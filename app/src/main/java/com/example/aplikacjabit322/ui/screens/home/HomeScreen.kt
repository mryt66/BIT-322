package com.example.aplikacjabit322.ui.screens.home

import androidx.compose.runtime.Composable

object HomeDestination {
    const val route = "home"
}

@Composable
fun HomeScreen(
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    navigateToItemDetails: (Long) -> Unit,
    navigateToHome: () -> Unit
) {
    //TODO
}

