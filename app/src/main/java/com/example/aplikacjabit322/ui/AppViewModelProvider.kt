package com.example.aplikacjabit322.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.aplikacjabit322.ui.screens.listHobbies.ListHobbiesViewModel
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesViewModel
import com.example.aplikacjabit322.ui.screens.login.LoginViewModel


object AppViewModelProvider {
    val Factory= viewModelFactory {
        initializer { LoginViewModel() }
        initializer { ListPreferencesViewModel() }
        initializer { ListHobbiesViewModel() }
//        initializer {
//            HomeViewModel(peopleApplication().container.itemsRepository)
//        }
//        initializer {
//            EntryViewModel(
//                this.createSavedStateHandle(),
//                itemsRepository =  peopleApplication().container.itemsRepository
//            )
//        }
//        initializer {
//            DetailsViewModel(
//                this.createSavedStateHandle(),
//                peopleApplication().container.itemsRepository
//            )
//
//        }
//        initializer {
//            EditViewModel(
//                this.createSavedStateHandle(),
//                peopleApplication().container.itemsRepository
//            )
//        }
    }
}

//fun CreationExtras.peopleApplication(): PeopleApplication =
//    (this[AndroidViewModelFactory.APPLICATION_KEY] as PeopleApplication)