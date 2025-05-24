# 🌤️ Weather Forecast App

A simple Android application that fetches and displays the current weather and a 5-day forecast for any searched city.

## 🧠 Objective

Create a mobile application that:
- Lets users search for any city.
- Displays the current weather conditions.
- Shows a 5-day weather forecast.
- Saved search history
- Pull-to-refresh
- Light/Dark theme toggle

## 📸 Screenshots

### 🔍 Search City Screen
![Search City](screenshot/search_city_screen.png)

### 🌙 Night Mode (Search Screen)
![Search Night Mode](screenshot/search_screen_night_mode.png)

### 🕓 Search History
![Search History](screenshot/History_screen.png)

## ✅ Features

### 🔍 Search
- Input field for city name.
- Search button to fetch weather details.

### ☁️ Weather Data
- Fetches real-time weather using the **OpenWeatherMap API**.
- Displays:
  - City Name
  - Current Temperature
  - Weather Condition (e.g., Sunny, Cloudy)
  - 5-Day Forecast (Date, Temperature, Condition)

### 🎨 UI/UX
- Clean and user-friendly interface using **Jetpack Compose**.
- Loading indicator while fetching data.
- Error handling for:
  - Invalid city names
  - No internet connection

 ## 🛠️ Tech Stack

- **Language**: Kotlin
- **Framework**: Jetpack Compose (Android)
- **API**: [OpenWeatherMap](https://openweathermap.org/api)
- **Architecture**: Clean Architecture
- **Tools**: Retrofit, Coroutines

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/jainurvashi/weather-forecate-app.git

## 🏗️ Clean Architecture Overview

This project uses **Clean Architecture** and follows a modular, layered structure:
- **Domain**: Contains business logic and use-cases.
- **Data**: Handles API calls, mapping DTOs to domain models.
- **Presentation**: Manages UI and state using Jetpack Compose.
- **DI**: Provides dependencies using Hilt (or your preferred DI framework).
