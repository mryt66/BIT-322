package com.example.aplikacjabit322.ui.screens.listPreferences

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class ListPreferencesViewModel : ViewModel() {
    var listPreferencesUiState by mutableStateOf(ListPreferencesUiState())
        private set

//    val name: StateFlow<String> = it

    fun ontagClick(tag: String) {
        // Zmiana stanu klikniętego tagu
        listPreferencesUiState = listPreferencesUiState.copy(
            clickedTags = listPreferencesUiState.clickedTags.toMutableMap().apply {
                this[tag] = !(this[tag] ?: false) // Przełącz stan kliknięcia
            }
        )
    }

    fun getSortedTags(tags: List<String>): List<String> {
        return tags.sortedWith(compareByDescending { listPreferencesUiState.clickedTags[it] ?: false })
    }

    fun questionUpdate(question: String) {
        listPreferencesUiState = listPreferencesUiState.copy(question = question)
    }
}

data class ListPreferencesUiState(
    val clickedTags: Map<String, Boolean> = emptyMap(), // Stan dla klikniętych tagów
    val question: String = "",
//    val nick: StateFlow<String> = mutableStateOf("")

)
