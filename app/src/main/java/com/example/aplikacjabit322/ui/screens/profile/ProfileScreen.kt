package com.example.aplikacjabit322.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aplikacjabit322.Bit322TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    navigateToItemDetails: (Long) -> Unit,
    navigateToHome: () -> Unit
) {

    Scaffold(
        topBar = {
            Bit322TopAppBar(
                title = "Witaj {NICK}!",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Edytuj hobby")
            }
        }
    }
}
