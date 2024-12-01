package com.example.aplikacjabit322.ui.screens.upload

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UploadViewModel : ViewModel() {

    var uploadUiState by mutableStateOf(UploadUiState())
        private set

    fun updatePhoto(photo: Uri?) {
        uploadUiState = uploadUiState.copy(photo = photo)
    }

    fun updateTitle(title: String) {
        uploadUiState = uploadUiState.copy(title = title)
    }

    fun updateDescription(description: String) {
        uploadUiState = uploadUiState.copy(description = description)
    }

    fun upload() {
        // upload to server
    }
}

data class UploadUiState(
    val nick: String = "",
    val photo: Uri? = null,
    val name: String = "",
    val description: String = "",
    val title: String = ""

)