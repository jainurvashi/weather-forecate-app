package com.example.weatherforecastapp.dataLayer.mapper

import com.example.weatherforecastapp.dataLayer.remote.dto.WeatherDto
import com.example.weatherforecastapp.domainLayer.model.WeatherResponse

fun WeatherDto.toCurrentWeather(): WeatherResponse {
    val weatherDetails = weather.firstOrNull()
    return WeatherResponse(
        cityName = name,
        countryCode = sys.country,
        temperature = main.temp,
        description = weatherDetails?.description?.replaceFirstChar { it.uppercase() } ?: "N/A",
        iconUrl = "https://openweathermap.org/img/wn/${weatherDetails?.icon}@2x.png",
        feelsLike = main.feels_like,
        tempMin = main.temp_min,
        tempMax = main.temp_max,
        humidity = main.humidity,
        windSpeed = wind.speed,
        cloudiness = clouds.all,
        weatherCondition = weather.firstOrNull()?.main ?: "Unknown"
    )
}