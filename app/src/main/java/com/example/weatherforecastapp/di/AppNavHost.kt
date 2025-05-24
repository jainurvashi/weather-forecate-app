package com.example.weatherforecastapp.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherforecastapp.presentationLayer.screen.history.HistoryScreen
import com.example.weatherforecastapp.presentationLayer.screen.weather.WeatherScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { WeatherScreen(navController = navController) }
        composable("searchHistory") { HistoryScreen(onBackClick = { navController.popBackStack() }) }
    }
}
