package com.example.phonebookapp.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.aplikacjabit322.ui.screens.home.HomeDestination
import com.example.aplikacjabit322.ui.screens.home.HomeScreen
import com.example.aplikacjabit322.ui.screens.listHobbies.ListHobbiesDestination
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesDestination
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesScreen
import com.example.aplikacjabit322.ui.screens.login.LoginDestination
import com.example.aplikacjabit322.ui.screens.login.LoginScreen

@Composable
fun AppBit322NavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination.route,
        modifier = modifier
    ) {


        composable(
            route = LoginDestination.route
        ) {
            LoginScreen(
                navigateBack = { navController.popBackStack() },
                navigateToListPreferences = {
                    navController.navigate(ListPreferencesDestination.route )
                }
            )
        }

        composable(
            route = HomeDestination.route,
            arguments = listOf(navArgument(HomeDestination.arg) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString(HomeDestination.arg)
            HomeScreen(login = login)
        }

        composable(
            route = ListPreferencesDestination.route,
            arguments = listOf(navArgument(ListPreferencesDestination.arg) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString(ListPreferencesDestination.arg)
            ListPreferencesScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToListHobbies = { navController.navigate(ListHobbiesDestination.route) }
            )
        }

        composable(
            route = ListHobbiesDestination.route,
            arguments = listOf(navArgument(ListHobbiesDestination.arg) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString(ListHobbiesDestination.arg)
            ListPreferencesScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToListHobbies = { navController.navigate(ListHobbiesDestination.route) }
            )
        }

//        composable(route = EntryDestination.route) {
//            EntryScreen(
//                onNavigateUp = { navController.navigateUp() },
//                navigateBack = { navController.popBackStack() },
//                navigateToItemDetails = { navController.navigate("${DetailsDestination.route}/${it}") },
//                navigateToHome = {
//                    navController.navigate(HomeDestination.route) {
//                        //delete everything in the backstack
//                        popUpTo(navController.graph.id) {
//                            inclusive = true
//                        }
//                    }
//
//                }
//            )
//        }
//        composable(
//            route = DetailsDestination.routeWithArgs,
//            arguments = listOf(navArgument(DetailsDestination.itemIdArg) {
//                type = NavType.IntType
//            })
//        ) {
//            DetailsScreen(
//                navigateToEditItem = { navController.navigate("${EditDestination.route}/$it") },
//                navigateBack = { navController.navigateUp() },
//                navigateHome = {//if popBackStack=1 then back to HomeScreen
//                    if (navController.popBackStack(HomeDestination.route, false)) {
//                        navController.navigateUp()
//                    } else {
//                        HomeDestination.route
//                    }
//                },
//            )
//        }
//        composable(
//            route = EditDestination.routeWithArgs,
//            arguments = listOf(navArgument(EditDestination.itemIdArg) {
//                type = NavType.IntType
//            })
//        ) {
//            EditScreen(
//                navigateBack = { navController.popBackStack() },
//                onNavigateUp = { navController.navigateUp() },
//                navigateToItemDetails = { navController.navigate("${DetailsDestination.route}/${it}") },
//                navigateToHome = {
//                    navController.navigate(HomeDestination.route) {
//                        //delete everything in the backstack
//                        popUpTo(navController.graph.id) {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
    }
}