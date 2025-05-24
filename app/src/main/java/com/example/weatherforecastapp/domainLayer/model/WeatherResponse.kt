package com.example.weatherforecastapp.domainLayer.model


data class WeatherResponse(
    val cityName: String,
    val countryCode: String,
    val temperature: Double,
    val description: String,
    val iconUrl: String,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int,
    val windSpeed: Double,
    val cloudiness: Int,
    val weatherCondition: String
)