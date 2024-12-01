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

    fun updateSelectedHobby(hobby: String) {
        hobbyUiState = hobbyUiState.copy(selectedHobby = hobby)
    }


}

data class HobbyUiState(
    val nick: String = "",
    val listOfVideos: List<String> = emptyList(),
    val listOfArticles: List<String> = emptyList(),
//    val listOfLikes: List<Int> = emptyList(),
//    val listOfUsernames: List<String> = emptyList(),
    val whichList: Int = 0,
    val listOfCommunities: List<Community> = emptyList(),
    val hobbies: List<String> = emptyList(),
    val selectedHobby: String = "",

)

data class Community(
    val description: String,
    val nick: String,
    val photoUrl: String,
    val title: String,
    val likes: Int
)
