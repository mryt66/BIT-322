package com.example.aplikacjabit322.ui.screens.listPreferences

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ListPreferencesViewModel : ViewModel() {
    var listPreferencesUiState by mutableStateOf(ListPreferencesUiState())
        private set

//    val name: StateFlow<String> = it

    fun ontagClick(tag: String) {
        // Zmiana stanu klikniętego tagu
        listPreferencesUiState = listPreferencesUiState.copy(
            clickedTags = listPreferencesUiState.clickedTags.toMutableMap().apply {
                this[tag] = !(this[tag] ?: false) // Przełącz stan kliknięcia
            }
        )
    }

    fun search(tag: String){
        if (tag.isNotBlank()){
            //szukaj
            listPreferencesUiState = listPreferencesUiState.copy(searchStatus = true)
        }


    }

    fun getSortedTags(tags: List<String>): List<String> {
        return tags.sortedWith(compareByDescending { listPreferencesUiState.clickedTags[it] ?: false })
    }

    fun questionUpdate(question: String) {
        listPreferencesUiState = listPreferencesUiState.copy(question = question)
    }


     suspend fun readAllPreferences(): MutableList<String> { //zczytuje wszystkie preferencje z bazy
        val allDataList: MutableList<String> = mutableListOf()
        val db = FirebaseFirestore.getInstance()
        Log.d("TAG", "readAllPreferences")
        try {
            // Pobranie wszystkich dokumentów z kolekcji "AllData"
            val result = db.collection("TAGS").get().await()

            // Iteracja po dokumentach
            for (document in result) {
//                val pref = document.getString("preferences") // "preferences" - poprawna nazwa klucza
//                if (pref != null) {
//                    listOfPreferences.add(pref)
//                }
                val data = document.data
                allDataList.add(data["tag"].toString())
                Log.d("TAG", data["tag"].toString())
            }
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
        }

        return allDataList
    }
}

data class ListPreferencesUiState(
    val clickedTags: Map<String, Boolean> = emptyMap(), // Stan dla klikniętych tagów
    val question: String = "",
    val searchStatus : Boolean = false
//    val nick: StateFlow<String> = mutableStateOf("")

)
