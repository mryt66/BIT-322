package com.example.aplikacjabit322.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.phonebookapp.ui.navigation.NavigationDestination


object LoginDestination : NavigationDestination {
    override val route = "ListPreferences/{itemId}"
    override val titleRes = "Login"
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}


@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    navigateToListPreferences: (String) -> Unit,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

//    val coroutineScope = rememberCoroutineScope()


    val loginDetails = viewModel.loginUiState

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EntryTextField(
                value = loginDetails.email,
                onValueChange = viewModel::emailUpdate,
                label = "email",
                isError = ""
            )
            EntryTextField(
                value = loginDetails.password,
                onValueChange = viewModel::passwordUpdate,
                label = "password",
                isError = ""
            )
            EntryTextField(
                value = loginDetails.nick,
                onValueChange = viewModel::nickUpdate,
                label = "nick",
                isError = ""
            )

            Button(onClick = {
                viewModel.saveToBase()
                navigateToListPreferences("Asdfa")
            }) {
                Text(text = "Save")
            }

        }


    }

}

@Composable
fun EntryTextField(
    value: String, onValueChange: (String) -> Unit, label: String, isError: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
//        textStyle = TextStyle(fontSize = (40-value.length).coerceIn(14, 24).sp),
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
        //Capitalization = KeyboardCapitalization.Words


    )


}

@Preview
@Composable
fun EntryTextFieldPreview() {
    EntryTextField(value = "Hello", onValueChange = {}, label = "Name", isError = "")
}