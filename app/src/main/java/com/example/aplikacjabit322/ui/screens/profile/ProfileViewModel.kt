package com.example.aplikacjabit322.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    var profileUiState by mutableStateOf(ProfileUiState())
        private set



}

data class ProfileUiState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val nick: String = ""
)