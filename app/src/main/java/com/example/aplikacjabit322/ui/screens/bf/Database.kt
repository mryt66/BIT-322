package com.example.aplikacjabit322.ui.screens.database

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.io.File

fun parseHobbyDataFromFile(filePath: String): Pair<String, List<String>> {
    val file = File(filePath)
    val lines = file.readLines()
    var hobbyName = ""
    val links = mutableListOf<String>()

    for (line in lines) {
        when {
            line.startsWith("Hobby:") -> {
                hobbyName = line.removePrefix("Hobby:").trim()
            }
            line.startsWith("Links:") -> {
                val linksString = line.removePrefix("Links:").trim()
                links.addAll(linksString.removeSurrounding("[", "]").split(", ").map { it.trim('\'') })
            }
        }
    }

    return Pair(hobbyName, links)
}

suspend fun readTagsFromDatabase(): MutableList<String> {
    val listOfHobby: MutableList<String> = mutableListOf()
    val db = FirebaseFirestore.getInstance()

    try {
        // kolejcja "USERS"
        val result = db.collection("TAGS").get().await()

        for (document in result) {
            val tag = document.getString("name") // "hobby" nazwa kolumny
            if (tag != null) {
                listOfHobby.add(tag)
            }
        }
    } catch (e: Exception) {
        println("Error fetching data: ${e.message}")
    }

    return listOfHobby
}

fun addHobbyToDatabase(name: String, artLink: List<String>) {
    val db = FirebaseFirestore.getInstance()

    val hobbyData = hashMapOf(
        "name" to name,
        "art_link" to artLink
    )

    try {
        db.collection("HOBBIES").add(hobbyData)
        println("Hobby added successfully")
    } catch (e: Exception) {
        println("Error adding hobby: ${e.message}")
    }
}

fun insertHobbyFromFile(filePath: String) {
    val (hobbyName, links) = parseHobbyDataFromFile(filePath)
    addHobbyToDatabase(hobbyName, links)
}