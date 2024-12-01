package com.example.aplikacjabit322.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aplikacjabit322.ui.screens.hobby.Community

class HomeViewModel: ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
    private set


}

data class HomeUiState(
    val nick: String = "",
    val listOfImages: List<Image> = emptyList(),
    val listOfArticles: List<Article> = emptyList(),
    val listOfCommunities: List<Community> = emptyList(),
    val listOfFunFacts: List<FunFact> = emptyList(),
)



data class Community(
    val description: String,
    val nick: String,
    val photoUrl: String,
    val title: String,
    val likes: Int
)

data class FunFact(
    val fact: String,
    val likes: Int
)

data class Article(
    val title: String,
    val content: String,
    val likes: Int
)

data class Image(
    val url: String,
    val likes: Int
)
