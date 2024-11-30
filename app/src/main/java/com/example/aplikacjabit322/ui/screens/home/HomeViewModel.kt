package com.example.aplikacjabit322.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
    private set


}

data class HomeUiState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val nick: String = ""
)