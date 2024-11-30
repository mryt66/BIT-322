package com.example.aplikacjabit322.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object HomeDestination {
    const val route = "home/{login}"
    const val arg = "login"
}


@Composable
fun HomeScreen(
    login: String?
) {
    // Możesz teraz używać zmiennej login w ekranie HomeScreen
    // Przykład:
    Text(text = "Welcome, $login")
}

