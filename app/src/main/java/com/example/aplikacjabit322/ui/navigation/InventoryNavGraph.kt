package com.example.phonebookapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.aplikacjabit322.ui.screens.hobby.HobbyDestination
import com.example.aplikacjabit322.ui.screens.hobby.HobbyScreen
import com.example.aplikacjabit322.ui.screens.home.HomeDestination
import com.example.aplikacjabit322.ui.screens.home.HomeScreen
import com.example.aplikacjabit322.ui.screens.listHobbies.ListHobbiesDestination
import com.example.aplikacjabit322.ui.screens.listHobbies.ListHobbiesScreen
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesDestination
import com.example.aplikacjabit322.ui.screens.listPreferences.ListPreferencesScreen
import com.example.aplikacjabit322.ui.screens.login.LoginDestination
import com.example.aplikacjabit322.ui.screens.login.LoginScreen
import com.example.aplikacjabit322.ui.screens.profile.ProfileDestination
import com.example.aplikacjabit322.ui.screens.profile.ProfileScreen
import com.example.aplikacjabit322.ui.screens.upload.UploadDestination
import com.example.aplikacjabit322.ui.screens.upload.UploadScreen

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
                    navController.navigate(ListPreferencesDestination.route)
                }
            )
        }

        composable(
            route = HomeDestination.route,
            arguments = listOf(navArgument(HomeDestination.arg) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString(HomeDestination.arg)
            HomeScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToProfile = { navController.navigate(ProfileDestination.route) },
                navigateToHobby = { navController.navigate(HobbyDestination.route) },
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateToUpload = { navController.navigate(UploadDestination.route) }
            )
        }

        composable(
            route = ListPreferencesDestination.route,
            arguments = listOf(navArgument(ListPreferencesDestination.arg) {
                type = NavType.StringType
            })
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
            arguments = listOf(navArgument(ListHobbiesDestination.arg) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString(ListHobbiesDestination.arg)
            ListHobbiesScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }

        composable(
            route = ProfileDestination.route,
            arguments = listOf(navArgument(ProfileDestination.arg) { type = NavType.StringType })
        ) {
            val login = it.arguments?.getString(ProfileDestination.arg)
            ProfileScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToListPreferences = { navController.navigate(ListPreferencesDestination.route) },
                navigateToListHobby = { navController.navigate(ListHobbiesDestination.route) }
            )
        }

        composable(
            route = UploadDestination.route,
            arguments = listOf(navArgument(UploadDestination.arg) { type = NavType.StringType })
        )
        { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString(UploadDestination.arg)
            UploadScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateToProfile = { navController.navigate(ProfileDestination.route) },
                navigateToHobby = { navController.navigate(HobbyDestination.route) },
                navigateToUpload = { navController.navigate(UploadDestination.route) }

            )
        }

        composable(
            route = HobbyDestination.route,
            arguments = listOf(navArgument(HobbyDestination.arg) { type = NavType.StringType })
        ){
            val login = it.arguments?.getString(HobbyDestination.arg)
            HobbyScreen(
                login = login,
                navigateBack = { navController.navigateUp() },
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateToProfile = { navController.navigate(ProfileDestination.route) },
                navigateToHobby = { navController.navigate(HobbyDestination.route) },
                navigateToUpload = { navController.navigate(UploadDestination.route) }
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