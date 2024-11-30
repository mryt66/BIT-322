package com.example.aplikacjabit322.ui.screens.ja

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aplikacjabit322.ui.screens.jessica.ItemUiState
import com.google.firebase.firestore.FirebaseFirestore

data class ListOf(
    val listOfHobby: MutableList<String> = mutableListOf(),
    val listOfChoosenHobby: MutableList<String> = mutableListOf(),
    val listChoosenLikes: MutableList<String> = mutableListOf(),
)


class newFunction(): ViewModel() {
    var ListOf by mutableStateOf(ListOf())
        private set

    private fun addToListPref(name: String ): Boolean{//wywołane przy zaznaczaniu checkboxa
        if (ListOf.listChoosenLikes.contains(name)) {
            ListOf = ListOf.copy(
                listChoosenLikes = ListOf.listChoosenLikes.toMutableList().apply {
                    remove(name)
                }
            )
            return false;
        } else {
            ListOf = ListOf.copy(
                listChoosenLikes = ListOf.listChoosenLikes.toMutableList().apply {
                    add(name)
                }
            )
            return true;
        }
    }

    private fun goToLogin(//przekazanei do kolejnego okna proeferencji
       list: List<String>,
    ) {//jakas akcja do przechodzenia do kolejnego okna musi przekazywac liste


    }



}