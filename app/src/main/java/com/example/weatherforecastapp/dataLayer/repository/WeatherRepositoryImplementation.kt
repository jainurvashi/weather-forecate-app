package com.example.weatherforecastapp.dataLayer.repository


import com.example.weatherforecastapp.dataLayer.remote.api.WeatherApi
import com.example.weatherforecastapp.dataLayer.remote.dto.ForecastDto
import com.example.weatherforecastapp.dataLayer.remote.dto.WeatherDto
import com.example.weatherforecastapp.domainLayer.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Named


class WeatherRepositoryImplementation @Inject constructor(
    private val api: WeatherApi,
    @Named("API_KEY") private val apiKey: String  // Inject API key via Hilt
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): WeatherDto {
        return api.getCurrentWeather(city, apiKey, units = "metric")
    }

    override suspend fun getForecastInformation(city: String): ForecastDto {
        return api.getCurrentForecast(city, apiKey, units = "metric")
    }
}
