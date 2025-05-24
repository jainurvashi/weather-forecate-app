package com.example.weatherforecastapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),      // vibrant purple
    secondary = Color(0xFF018786),    // dark teal
    tertiary = Color(0xFFB00020)      // red-ish accent
)

private val DarkColorScheme = darkColorScheme(
    primary =Color(0xFF1E1E1E),      // soft purple
    secondary = Color(0xFF03DAC6),    // tealish accent
    tertiary = Color(0xFFFF4081)      // pink accent
)

@Composable
fun WeatherForecastAppTheme(
    darkTheme: Boolean,  // Auto theme detection
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,  // import your Typography object
        content = content
    )
}