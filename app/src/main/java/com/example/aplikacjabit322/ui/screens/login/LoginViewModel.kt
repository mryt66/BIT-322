package com.example.aplikacjabit322.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore



class LoginViewModel : ViewModel(){
    var name = "nazwa Ja"
    var mail = "gmail"
    var login = "1234"
    var loginUiState by mutableStateOf(LoginUiState())
        private set

    companion object {
        public var login = ""
    }

    fun saveToBase() {
        //TODO
        val db = FirebaseFirestore.getInstance()

        val userData = hashMapOf(
            "name" to name,
            "mail" to mail,
            "login" to login
        )

        db.collection("USERS")
            .add(userData) // Zapisujemy dokument z automatycznie wygenerowanym ID
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }

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
            LoginViewModel.login = nick
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