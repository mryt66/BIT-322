package com.example.aplikacjabit322.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun saveToBase() {
        //TODO
    }

    fun emailUpdate(email: String) {
        if (email.length < 20) {
            loginUiState = loginUiState.copy(email = email)
        }
    }

    fun passwordUpdate(password: String) {
        if (password.length < 20) {
            loginUiState = loginUiState.copy(password = password)
        }
    }

    fun nickUpdate(nick: String) {
        if (nick.length < 20) {
            loginUiState = loginUiState.copy(nick = nick)
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val nick: String = ""
//    val itemList: List<Item> = listOf(),
//    val alphabetItemLists: List<List<Item>> = listOf(),
//    val searchValue: String = "",
//    val categoryValue: Category = Category.NONE
)