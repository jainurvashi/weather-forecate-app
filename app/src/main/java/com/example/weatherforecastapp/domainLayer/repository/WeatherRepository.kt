package com.example.weatherforecastapp.domainLayer.repository

import com.example.weatherforecastapp.dataLayer.remote.dto.ForecastDto
import com.example.weatherforecastapp.dataLayer.remote.dto.WeatherDto
import com.example.weatherforecastapp.domainLayer.model.WeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): WeatherDto
    suspend fun getForecastInformation(city: String): ForecastDto
}