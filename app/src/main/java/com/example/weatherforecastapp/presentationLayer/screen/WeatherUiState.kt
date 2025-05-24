package com.example.weatherforecastapp.presentationLayer.screen

import com.example.weatherforecastapp.domainLayer.model.WeatherResponse

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val data: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
    object Idle : WeatherUiState()
}
