package com.example.weatherforecastapp.dataLayer.repository


import com.example.weatherforecastapp.dataLayer.remote.api.WeatherApi
import com.example.weatherforecastapp.domainLayer.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi,
        @Named("API_KEY") apiKey: String
    ): WeatherRepository {
        return WeatherRepositoryImplementation(api, apiKey)
    }
}
