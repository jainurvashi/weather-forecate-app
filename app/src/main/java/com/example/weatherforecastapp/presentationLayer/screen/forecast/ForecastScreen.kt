package com.example.weatherforecastapp.presentationLayer.screen.forecast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.domainLayer.model.ForecastResponse
import com.example.weatherforecastapp.presentationLayer.common.DateChip


@Composable
fun ForecastScreen(forecastResponse: ForecastResponse) {
    val forecastList = forecastResponse.forecastList
    var selectedDate by remember { mutableStateOf(forecastList.firstOrNull()?.date ?: "") }
    Column(modifier = Modifier.fillMaxSize()) {
        val uniqueDates = forecastList.map { it.date }.distinct()

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uniqueDates) { date ->
                val isSelected = date == selectedDate
                DateChip(
                    date = date,
                    selected = isSelected
                ) {
                    selectedDate = date
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val selectedForecast = forecastList.filter { it.date == selectedDate }

        LazyColumn {
            items(selectedForecast) { forecast ->
                ForecastInfoCard(
                    cityName = forecastResponse.cityName,
                    temp = forecast.temp,
                    condition = forecast.condition,
                    time = forecast.time
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

