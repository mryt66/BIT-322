package com.example.aplikacjabit322.ui.screens.hobby

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HobbyViewModel : ViewModel() {
    var hobbyUiState by mutableStateOf(HobbyUiState())
        private set

    fun changeList(whichList: Int) {
        hobbyUiState = hobbyUiState.copy(whichList = whichList)
    }
}

data class HobbyUiState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val nick: String = "",
    val listOfVideos: List<String> = emptyList(),
    val listOfArticles: List<String> = emptyList(),
    val listOfLikes: List<Int> = emptyList(),
    val listOfUsernames: List<String> = emptyList(),
    val whichList: Int = 0,
    val listOfCommunities: List<Community> = emptyList()
)

data class Community(
    val description: String,
    val nick: String,
    val photoUrl: String
)
