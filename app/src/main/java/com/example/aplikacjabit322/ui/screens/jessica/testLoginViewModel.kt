package com.example.aplikacjabit322.ui.screens.jessica

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

data class ItemUiState(
    val isEntryValid: Boolean = false,
    val isEnabledMore: Boolean = true,
    val enabledUsed: Int = 0,
    val nameError: String = "",
    val emailError: String = "",
)
class testLoginViewModel(): ViewModel(){
    var itemUiState by mutableStateOf(ItemUiState())
        private set

val adress = "ja.gmail.com";

    private fun isValidEmail(email: String): Boolean {//sprawdza maila czy ma dobra postac
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()) {
            itemUiState = itemUiState.copy(
                emailError = "Invalid email"
            )
            return false
        }
        itemUiState = itemUiState.copy(
            emailError = ""
        )
        return true
    }

    private fun isValidName(name : String): Boolean{//sprawdza czy imie istnieje juz w bazie
        if(NameInBase(name)){
            itemUiState = itemUiState.copy(
                nameError = "Invalid email"
            )
            return false
        }
        itemUiState = itemUiState.copy(
            nameError = ""
        )
        return true
    }


    private fun NameInBase(name: String): Boolean {
        var exists = false

        // Referencja do Firestore
        val db = FirebaseFirestore.getInstance()

        // Sprawdzenie, czy imię istnieje
        db.collection("users") // Kolekcja, w której trzymasz użytkowników
            .whereEqualTo("name", name) // Filtruj według pola "name"
            .get()
            .addOnSuccessListener { documents ->
                // Jeśli dokumenty istnieją, imię jest w bazie
                exists = !documents.isEmpty
            }
            .addOnFailureListener { e ->
                // Obsłuż błędy
                println("Błąd przy wyszukiwaniu w Firestore: $e")
            }

        return exists
    }

}