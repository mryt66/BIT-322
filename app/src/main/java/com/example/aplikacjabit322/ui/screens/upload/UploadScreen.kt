package com.example.aplikacjabit322.ui.screens.upload

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.Bit322TopAppBar
import com.example.aplikacjabit322.ui.AppViewModelProvider

object UploadDestination {
    const val route = "upload/{login}"
    const val arg = "login"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(
    login: String?,
    navigateBack: () -> Unit,
    viewModel: UploadViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            Bit322TopAppBar(
                title = "UploadPhoto",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) {
        Column (
            Modifier.padding(it)
        ){
            //TODO
        }

    }

}