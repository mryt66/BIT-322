package com.example.aplikacjabit322.ui.screens.jessica

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Hello() {


}

@Composable
fun EntryTextField(value: String, onValueChange: (String) -> Unit, label: String, isError: String
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