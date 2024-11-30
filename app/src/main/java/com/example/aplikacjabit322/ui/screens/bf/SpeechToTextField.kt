package com.example.aplikacjabit322.ui.components

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun SpeechToTextTest() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    SpeechToTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Enter text or use mic") },
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

@Composable
fun SpeechToTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    // Get the current context and cast it to an Activity
    val context = LocalContext.current
    val activity = context as Activity

    // Create a SpeechRecognizer instance
    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    
    // Create an intent for speech recognition
    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    }

    // Create a launcher for the speech recognition activity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result of the speech recognition activity
        if (result.resultCode == Activity.RESULT_OK) {
            val matches = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!matches.isNullOrEmpty()) {
                // Update the text field with the recognized speech
                onValueChange(TextFieldValue(matches[0]))
            }
        } else {
            // Show a toast message if speech recognition failed
            Toast.makeText(context, "Speech recognition failed", Toast.LENGTH_SHORT).show()
        }
    }

    // Layout for the text field and button
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text field for user input
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Button to start speech recognition
        Button(onClick = {
            launcher.launch(speechRecognizerIntent)
        }) {
            Text("Start Speaking")
        }
    }
}