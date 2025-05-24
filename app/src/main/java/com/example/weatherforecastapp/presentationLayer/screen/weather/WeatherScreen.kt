package com.example.weatherforecastapp.presentationLayer.screen.weather

import android.widget.Toast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherforecastapp.presentationLayer.common.AppBar
import com.example.weatherforecastapp.presentationLayer.common.CenteredProgressBar
import com.example.weatherforecastapp.presentationLayer.screen.ForecastUiState
import com.example.weatherforecastapp.presentationLayer.screen.WeatherUiState
import com.example.weatherforecastapp.presentationLayer.screen.forecast.ForecastScreen
import com.example.weatherforecastapp.presentationLayer.viewModel.HistoryViewModel
import com.example.weatherforecastapp.presentationLayer.viewModel.WeatherViewModel
import com.example.weatherforecastapp.ui.theme.WeatherForecastAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun WeatherScreen(modifier: Modifier = Modifier,viewModel: WeatherViewModel = hiltViewModel(),navController: NavHostController) {
    val context  = LocalContext.current
    var isDarkTheme by remember { mutableStateOf(false) }
    var cityQuery by remember { mutableStateOf("") }
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val refreshErrorEvent = viewModel.refreshErrorEvent
    val historyViewModel :HistoryViewModel = hiltViewModel()
    LaunchedEffect(refreshErrorEvent) {
        refreshErrorEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    WeatherForecastAppTheme(darkTheme = isDarkTheme) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                AppBar(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onShowHistoryClick = { navController.navigate("searchHistory") }

                )
            }

        ) { innerPadding ->
            Column(modifier = modifier.padding(innerPadding)) {
                SearchBar(
                    query = cityQuery,
                    onQueryChanged = { cityQuery = it},
                    onSearchClicked = {
                        if(cityQuery.isEmpty()){
                            Toast.makeText(context,"Please enter Value",Toast.LENGTH_SHORT).show()

                        }else{
                            viewModel.fetchWeather(cityQuery)
                            viewModel.fetchForecast(cityQuery)

                        }
                        historyViewModel.addQuery(cityQuery)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = {
                        viewModel.refreshAll(cityQuery)
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        when (val state = viewModel.weatherUiState) {
                            is WeatherUiState.Loading -> {
                            }
                            is WeatherUiState.Success -> {
                                WeatherInfoCard(weather = state.data)
                            }
                            is WeatherUiState.Error -> {
                                Toast.makeText(context,"Error: ${state.message}",Toast.LENGTH_SHORT).show()
                            }
                            is WeatherUiState.Idle -> {
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        when (val forecastState = viewModel.forecastUiState) {
                            is ForecastUiState.Loading -> {
                                CenteredProgressBar()
                            }
                            is ForecastUiState.Success -> {
                                ForecastScreen(forecastResponse = forecastState.data)
                            }
                            is ForecastUiState.Error -> {
                            }
                            is ForecastUiState.Idle -> {
                            }
                        }
                    }

                }


            }
        }
    }
}

