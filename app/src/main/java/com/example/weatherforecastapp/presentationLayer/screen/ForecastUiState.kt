package com.example.weatherforecastapp.presentationLayer.screen

import com.example.weatherforecastapp.domainLayer.model.ForecastResponse

sealed class ForecastUiState {
    object Loading : ForecastUiState()
    data class Success(val data: ForecastResponse) : ForecastUiState()
    data class Error(val message: String) : ForecastUiState()
    object Idle : ForecastUiState()
}
