package com.example.weatherforecastapp.dataLayer.remote.api

import com.example.weatherforecastapp.dataLayer.remote.dto.ForecastDto
import com.example.weatherforecastapp.dataLayer.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherDto

    @GET("forecast")
    suspend fun getCurrentForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): ForecastDto
}