package com.example.aplikacjabit322

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplikacjabit322.ui.screens.database.insertHobbyFromFile
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesScreen
import com.example.aplikacjabit322.ui.screens.login.LoginScreen
import com.example.aplikacjabit322.ui.screens.map.FullMapScreen
//import com.example.aplikacjabit322.ui.screens.map.MapFragment
import com.example.aplikacjabit322.ui.screens.map.MapScreen
import com.example.aplikacjabit322.ui.screens.profile.ThemeScreen
import com.example.aplikacjabit322.ui.theme.AplikacjaBit322Theme
import com.example.aplikacjabit322.ui.theme.ThemePreferences
import com.example.aplikacjabit322.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themePreferences = ThemePreferences(applicationContext)
            val themeViewModel = ThemeViewModel(themePreferences)

            setContent {
                val isDarkMode by themeViewModel.isDarkMode.collectAsState()

                AplikacjaBit322Theme(darkTheme = isDarkMode) { // Przekaż tryb ciemny do motywu
//                    ThemeScreen(themeViewModel = themeViewModel)

                    AppBit322()

//                    MapFragment()
                }
            }
//            AplikacjaBit322Theme {
//                // A surface container using the 'background' color from the theme
////                Surface(
////                    modifier = Modifier.fillMaxSize(),
////                    color = MaterialTheme.colorScheme.background
////                ) {
////                    Greeting("Android")
////                }
////                ListPreferencesScreen(
////                    onNavigateUp = { /*TODO*/ },
////                    navigateBack = { /*TODO*/ },
//////                    navigateToItemDetails = ,
////                    navigateToHome = { /*TODO*/ })
////                LoginScreen(
////                    onNavigateUp = { /*TODO*/ },
////                    navigateBack = { /*TODO*/ },
////                    navigateToListPreferences = { },
////                    navigateToHome = { /*TODO*/ }
////                )
//                AppBit322()
//
//            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplikacjaBit322Theme {
        Greeting("Android")
    }
}