package com.example.weatherforecastapp.dataLayer.remote.dto

data class ForecastDto(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherForecastItem>,
    val city: City
)

data class WeatherForecastItem(
    val dt: Long,
    val main: MainForecast,
    val weather: List<WeatherDescription>,
    val clouds: CloudsForecast,
    val wind: WindForecast,
    val visibility: Int,
    val pop: Double,
    val sys: SysForecast,
    val dt_txt: String
)

data class MainForecast(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CloudsForecast(
    val all: Int
)

data class WindForecast(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class SysForecast(
    val pod: String
)

data class City(
    val id: Int,
    val name: String,
    val coord: CoordForecast,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class CoordForecast(
    val lat: Double,
    val lon: Double
)
