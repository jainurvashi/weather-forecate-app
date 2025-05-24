package com.example.weatherforecastapp.domainLayer.model

data class ForecastResponse(
    val cityName: String,
    val currentTemp: Double,
    val currentCondition: String,
    val forecastList: List<DailyForecastUIModel>
)

data class DailyForecastUIModel(
    val date: String,
    val time: String,
    val temp: Double,
    val condition: String
)