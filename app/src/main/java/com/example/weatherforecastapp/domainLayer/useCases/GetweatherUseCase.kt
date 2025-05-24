package com.example.weatherforecastapp.domainLayer.useCases


import com.example.weatherforecastapp.dataLayer.mapper.toCurrentWeather
import com.example.weatherforecastapp.dataLayer.mapper.toForecast
import com.example.weatherforecastapp.domainLayer.model.ForecastResponse
import com.example.weatherforecastapp.domainLayer.model.WeatherResponse
import com.example.weatherforecastapp.domainLayer.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun getWeather(city: String): WeatherResponse {
        val dto =  repository.getCurrentWeather(city)
        return dto.toCurrentWeather()

    }
    suspend fun getForecast(city: String): ForecastResponse {
        val dto =  repository.getForecastInformation(city)
        return dto.toForecast()

    }
}
