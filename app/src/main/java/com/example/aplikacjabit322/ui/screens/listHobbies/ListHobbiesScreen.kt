package com.example.aplikacjabit322.ui.screens.listHobbies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.Bit322TopAppBar
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesViewModel

object ListHobbiesDestination {
    const val route = "listHobbies/{login}"
    const val arg = "login"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHobbiesScreen(
    login: String?,
    navigateBack: () -> Unit,
    navigateToHome: (String) -> Unit,
    viewModel: ListHobbiesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val tags = listOf("Sport", "Muzyka", "Filmy", "Gry", "Gotowanie", "Podróże")
    val clickedTags = viewModel.listHobbiesUiState.clickedTags // Pobranie stanu klikniętych tagów
    val sortedTags = viewModel.getSortedTags(tags) // Uzyskanie posortowanej listy tagów

    val hobbiesDetails = viewModel.listHobbiesUiState

    Scaffold(
        topBar = {
            Bit322TopAppBar(
                title = "Hobby",
                canNavigateBack = true,
                navigateUp = navigateBack,
                canClickButton = true,
                onClickButton = {
                    navigateToHome(login ?: "null")
                },
                buttonIcon = Icons.Default.Done
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Text(
                text = "Proponowane Hobby",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
//            PreferencesTextField(value = hobbiesDetails.question, onValueChange = viewModel::questionUpdate, label = "Co lubisz robić?", isError = "")

            PreferencesTags(
                tags = sortedTags,
                clickedTags = clickedTags,
                onTagClick = viewModel::ontagClick
            )
        }
    }
}

@Composable
fun PreferencesTextField(
    value: String, onValueChange: (String) -> Unit, label: String, isError: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        label = { Text(label) },
        modifier = Modifier
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
            .widthIn(max = OutlinedTextFieldDefaults.MinWidth),
        maxLines = 2,
        isError = isError.isNotBlank(),
        trailingIcon = {
            if (isError.isNotBlank()) {
                Icon(
                    imageVector = Icons.Default.Warning, contentDescription = null, tint = Color.Red
                )
            }
        },
        supportingText = {
            if (isError.isNotBlank()) {
                Text(
                    text = isError, color = Color.Red
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when (label) {
                "Phone" -> KeyboardType.Phone
                "Email" -> KeyboardType.Email
                else -> KeyboardType.Text
            },
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences
        ),
    )
}

@Composable
fun PreferencesTags(
    tags: List<String>,
    clickedTags: Map<String, Boolean>,
    onTagClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        tags.forEach { tag ->
            val isClicked = clickedTags[tag] ?: false
            val buttonColor = if (isClicked) Color.Green else MaterialTheme.colorScheme.primary

            Button(
                onClick = { onTagClick(tag) },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                )
            ) {
                Text(text = tag)
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ListHobbiesScreenPreview() {
//    ListHobbiesScreen(
//        onNavigateUp = { /*TODO*/ },
//        navigateBack = { /*TODO*/ },
//        navigateToHome = { /*TODO*/ }
//    )
//}