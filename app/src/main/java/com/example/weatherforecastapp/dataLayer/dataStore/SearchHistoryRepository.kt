package com.example.weatherforecastapp.dataLayer.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val SEARCH_HISTORY_KEY = stringSetPreferencesKey("search_history")

    // Save a new search query
    suspend fun addSearchQuery(query: String) {
        dataStore.edit { prefs ->
            val currentSet = prefs[SEARCH_HISTORY_KEY] ?: emptySet()
            val updatedSet = currentSet.toMutableSet()
            updatedSet.add(query)
            prefs[SEARCH_HISTORY_KEY] = updatedSet
        }
    }

    // Get all search queries as Flow
    val searchHistoryFlow: Flow<List<String>> = dataStore.data
        .map { prefs ->
            prefs[SEARCH_HISTORY_KEY]?.toList() ?: emptyList()
        }
}
