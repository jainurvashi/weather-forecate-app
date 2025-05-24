package com.example.weatherforecastapp.presentationLayer.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(isDarkTheme: Boolean, onToggleTheme: () -> Unit,onShowHistoryClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Forecast", color = Color.White)
        },
        actions = {
            IconButton(onClick = onShowHistoryClick) {
                Icon(
                    imageVector = Icons.Filled.History,
                    contentDescription = "Show History",
                    tint = Color.White
                )
            }
            IconButton(onClick = onToggleTheme) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Default.WbSunny else Icons.Default.Nightlight,
                    contentDescription = "Toggle Theme",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}


