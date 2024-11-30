package com.example.aplikacjabit322.ui.screens.listHobbies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ListHobbiesViewModel : ViewModel() {
    var listHobbiesUiState by mutableStateOf(ListHobbiesUiState())
        private set

    fun ontagClick(tag: String) {
        // Zmiana stanu klikniętego tagu
        listHobbiesUiState = listHobbiesUiState.copy(
            clickedTags = listHobbiesUiState.clickedTags.toMutableMap().apply {
                this[tag] = !(this[tag] ?: false) // Przełącz stan kliknięcia
            }
        )
    }

    fun getSortedTags(tags: List<String>): List<String> {
        return tags.sortedWith(compareByDescending { listHobbiesUiState.clickedTags[it] ?: false })
    }

    fun questionUpdate(question: String) {
        listHobbiesUiState = listHobbiesUiState.copy(question = question)
    }
}

data class ListHobbiesUiState(
    val clickedTags: Map<String, Boolean> = emptyMap(), // Stan dla klikniętych tagów
    val question: String = ""
)