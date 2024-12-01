package com.example.aplikacjabit322.ui.screens.profile

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.Bit322TopAppBar
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.aplikacjabit322.ui.screens.listHobbies.ListHobbiesViewModel
import com.example.aplikacjabit322.ui.theme.AplikacjaBit322Theme
import com.example.aplikacjabit322.ui.theme.ThemePreferences
import com.example.aplikacjabit322.ui.theme.ThemeViewModel

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
    // Uzyskaj kontekst lokalny
    val context = androidx.compose.ui.platform.LocalContext.current

    // Utwórz ThemePreferences i ViewModel
    val themePreferences = ThemePreferences(context)
    val themeViewModel = ThemeViewModel(themePreferences)

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
            // Przycisk do edycji hobby
            Button(onClick = { navigateToListHobby(login ?: "null") }) {
                Text(text = "Edytuj swoje hobby")
            }

            // Przycisk do edycji preferencji
            Button(onClick = { navigateToListPreferences(login ?: "null") }) {
                Text(text = "Edytuj swoje preferencje")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Przełącznik trybu ciemnego
            ThemeScreen(themeViewModel = themeViewModel)
        }
    }
}

@Composable
fun ThemeScreen(themeViewModel: ThemeViewModel) {
    val isDarkMode by themeViewModel.isDarkMode.collectAsState() // Odbierz stan trybu ciemnego

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enable Dark Mode", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Switch(
            checked = isDarkMode,
            onCheckedChange = { themeViewModel.toggleDarkMode(it) }
        )
    }
}
