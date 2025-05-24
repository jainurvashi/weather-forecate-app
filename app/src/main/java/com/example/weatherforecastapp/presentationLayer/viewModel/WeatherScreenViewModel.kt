package com.example.weatherforecastapp.presentationLayer.viewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.domainLayer.useCases.GetWeatherUseCase
import com.example.weatherforecastapp.presentationLayer.screen.ForecastUiState
import com.example.weatherforecastapp.presentationLayer.screen.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    var weatherUiState by mutableStateOf<WeatherUiState>(WeatherUiState.Idle)
        private set
    var forecastUiState by mutableStateOf<ForecastUiState>(ForecastUiState.Idle)
        private set
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing
    private val _refreshErrorEvent = MutableSharedFlow<String>()
    val refreshErrorEvent = _refreshErrorEvent.asSharedFlow()
    fun fetchWeather(city: String) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            try {
                val result = getWeatherUseCase.getWeather(city)
                weatherUiState = WeatherUiState.Success(result)
            } catch (e: Exception) {
                weatherUiState = WeatherUiState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

    fun fetchForecast(city: String) {
        viewModelScope.launch {
            forecastUiState = ForecastUiState.Loading
            try {
                val result = getWeatherUseCase.getForecast(city)
                forecastUiState = ForecastUiState.Success(result)
            } catch (e: Exception) {
                forecastUiState = ForecastUiState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

    fun refreshAll(city: String) {
        if (city.isBlank()) {
            viewModelScope.launch {
                _refreshErrorEvent.emit("Enter city to refresh")
            }
            return
        }

        viewModelScope.launch {
            _isRefreshing.value = true

            // Clear old data to update UI immediately
            weatherUiState = WeatherUiState.Idle
            forecastUiState = ForecastUiState.Idle

            try {
                val weatherDeferred = async { getWeatherUseCase.getWeather(city) }
                val forecastDeferred = async { getWeatherUseCase.getForecast(city) }

                val weatherResult = weatherDeferred.await()
                val forecastResult = forecastDeferred.await()

                weatherUiState = WeatherUiState.Success(weatherResult)
                forecastUiState = ForecastUiState.Success(forecastResult)

            } catch (e: Exception) {
                val message = e.localizedMessage ?: "An error occurred"
                weatherUiState = WeatherUiState.Error(message)
                forecastUiState = ForecastUiState.Error(message)
                _refreshErrorEvent.emit(message)  // Show Toast on error
            } finally {
                _isRefreshing.value = false
            }
        }
    }

}
