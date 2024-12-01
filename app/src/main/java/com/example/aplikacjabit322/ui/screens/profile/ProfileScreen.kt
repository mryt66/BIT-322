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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.Bit322TopAppBar
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.aplikacjabit322.ui.screens.listHobbies.ListHobbiesViewModel

object ProfileDestination {
    const val route = "profile/{login}"
    const val arg = "login"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    login: String?,
    navigateBack: () -> Unit,
    navigateToListPreferences: (String) -> Unit,
    navigateToListHobby: (String) -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold(
        topBar = {
            Bit322TopAppBar(
                title = "Witaj $login",
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
            Button(onClick = { navigateToListHobby(login?: "null") }) {
                Text(text = "Edytuj swoje hobby")
            }

            Button(onClick = { navigateToListPreferences(login?: "null") }) {
                Text(text = "Edytuj swoje preferencje")
            }


        }
    }
}
