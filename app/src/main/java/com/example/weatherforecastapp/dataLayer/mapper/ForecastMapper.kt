package com.example.weatherforecastapp.dataLayer.mapper

import com.example.weatherforecastapp.dataLayer.remote.dto.ForecastDto
import com.example.weatherforecastapp.domainLayer.model.DailyForecastUIModel
import com.example.weatherforecastapp.domainLayer.model.ForecastResponse
fun ForecastDto.toForecast(): ForecastResponse {
    val cityName = this.city.name

    // Current weather from the first forecast item (or default values)
    val currentWeatherItem = this.list.firstOrNull()
    val currentTemp = currentWeatherItem?.main?.temp ?: 0.0
    val currentCondition = currentWeatherItem?.weather?.firstOrNull()?.main ?: "Unknown"

    // Map each forecast item to DailyForecastUIModel with date only (yyyy-MM-dd)
    val forecastList = this.list.map { item ->
        DailyForecastUIModel(
            date = item.dt_txt.substring(0, 10), // Extract only date part
            temp = item.main.temp,
            condition = item.weather.firstOrNull()?.main ?: "Unknown",
            time = item.dt_txt.substring(11, 16),// "15:00"
        )
    }

    return ForecastResponse(
        cityName = cityName,
        currentTemp = currentTemp,
        currentCondition = currentCondition,
        forecastList = forecastList
    )
}

