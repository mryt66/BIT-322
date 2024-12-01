package com.example.aplikacjabit322.ui.screens.upload

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.aplikacjabit322.ui.screens.home.HomeViewModel

object UploadDestination {
    const val route = "upload/{login}"
    const val arg = "login"
}

@Composable
fun UploadScreen(
    login: String?,
    navigateBack: () -> Unit,
    viewModel: UploadViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

}