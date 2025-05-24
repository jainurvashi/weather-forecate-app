package com.example.weatherforecastapp.presentationLayer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.dataLayer.dataStore.SearchHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: SearchHistoryRepository
) : ViewModel() {
    val searchHistory = repository.searchHistoryFlow

    fun addQuery(query: String) {
        viewModelScope.launch {
            repository.addSearchQuery(query)
        }
    }
}

