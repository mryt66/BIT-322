package com.example.aplikacjabit322.ui.screens.upload

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.aplikacjabit322.ui.screens.hobby.TopAppBarHobby

object UploadDestination {
    const val route = "upload/{login}"
    const val arg = "login"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(
    login: String?,
    navigateBack: () -> Unit,
    navigateToHome: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToHobby: (String) -> Unit,
    navigateToUpload: (String) -> Unit,
    viewModel: UploadViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            TopAppBarHobby(
                navigateBack = navigateBack,
                navigateToHome = { navigateToHome(login ?: "null") },
                navigateToProfile = { navigateToProfile(login ?: "null") },
                navigateToSearch = { navigateToHobby(login ?: "null") },
                navigateToUpload = { navigateToUpload(login ?: "null") },
                isRow = true
            )
        }
    ) {
        Column(
            Modifier.padding(it).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EntryPhoto(
                uploadUiState = viewModel.uploadUiState,
                onPhotoChange = { uri -> viewModel.updatePhoto(uri) } // Poprawne przekazanie aktualizacji
            )
            EntryTitle(
                uploadUiState = viewModel.uploadUiState,
                onTitleChange = { title -> viewModel.updateTitle(title) }
            )
            EntryDescription(
                uploadUiState = viewModel.uploadUiState,
                onDescriptionChange = { description -> viewModel.updateDescription(description) }
            )
            ButtonUpload(
                uploadUiState = viewModel.uploadUiState,
                onUploadClick = {
                    viewModel.upload()
                    navigateToHome(login ?: "null")
                }
            )
        }
    }
}

@Composable
fun ButtonUpload(
    uploadUiState: UploadUiState,
    onUploadClick: () -> Unit
) {
    Button(
        onClick = onUploadClick,
        enabled = uploadUiState.photo != null && uploadUiState.name.isNotEmpty()
    ) {
        Text("Upload")
    }
}

@Composable
fun EntryTitle(
    uploadUiState: UploadUiState,
    onTitleChange: (String) -> Unit
) {
    Text(
        text = "Title",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(16.dp)
    )
    OutlinedTextField(
        value = uploadUiState.title,
        onValueChange = { newValue ->
            onTitleChange(newValue)
        },
        label = { Text("Enter title") },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        singleLine = false,
        maxLines = 5,
    )
}

@Composable
fun EntryDescription(
    uploadUiState: UploadUiState,
    onDescriptionChange: (String) -> Unit
) {
    Text(
        text = "Description",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(16.dp)
    )
    OutlinedTextField(
        value = uploadUiState.description,
        onValueChange = { newValue ->
            onDescriptionChange(newValue)
        },
        label = { Text("Enter description") },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        singleLine = false,
        maxLines = 5,
    )
}


@Composable
fun EntryPhoto(
    uploadUiState: UploadUiState,
    onPhotoChange: (Uri?) -> Unit // Zmiana argumentu funkcji
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            onPhotoChange(uri) // Wywołanie funkcji przekazanej w argumencie
        }
    }

    val painter: Painter = rememberAsyncImagePainter(model = uploadUiState.photo.toString())

    Card(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(96.dp))
            .size(192.dp)
    ) {
        if (uploadUiState.photo != null) {
            Image(
                painter = painter,
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            val initial = if (uploadUiState.name.isNotEmpty()) {
                uploadUiState.name.first().uppercaseChar().toString()
            } else {
                ""
            }
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initial, style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }

    Button(onClick = {
        pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }) {
        Text("Add Photo")
    }
}
