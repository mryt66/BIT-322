package com.example.aplikacjabit322.ui.screens.login

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.R
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.phonebookapp.ui.navigation.NavigationDestination

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


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

//            Image(imageVector =
//                , contentDescription = )
            DuckAnimation()

//
//            EntryTextField2(
//                value = loginDetails.email,
//                onValueChange = viewModel::emailUpdate,
//                label = "email",
//                isError = ""
//            )
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
fun DuckAnimation() {
    // Stan dla wartości pola tekstowego
    var textFieldValue by remember { mutableStateOf("") }

    // `Animatable` dla płynnego ruchu
    val offsetX = remember { androidx.compose.animation.core.Animatable(0f) }
    val offsetY = remember { androidx.compose.animation.core.Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Obraz kaczuszki
            Image(
                painter = painterResource(id = R.drawable.duck),
                contentDescription = "Duck",
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            offsetX.value.toInt(),
                            offsetY.value.toInt()
                        )
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Funkcja do animacji ruchu kaczki
        fun moveDuck() {
            coroutineScope.launch {
                offsetX.animateTo(
                    targetValue = offsetX.value + 50f,
                    animationSpec = tween(500)
                )
                offsetY.animateTo(
                    targetValue = offsetY.value - 30f,
                    animationSpec = tween(500)
                )
            }
        }

        // Pole tekstowe, które aktualizuje tekst i przesuwa kaczuszkę
        EntryTextField2(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue // Aktualizacja tekstu
                moveDuck() // Wywołanie animacji
            },
            label = "email",
            isError = ""
        )
    }
}

@Composable
fun EntryTextField2(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue) // Aktualizacja tekstu i wywołanie dodatkowych działań
        },
        label = { Text(label) },
        modifier = Modifier
            .padding(8.dp)
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
        )
    )
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