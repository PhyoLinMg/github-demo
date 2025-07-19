package dev.linmaung.github

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.linmaung.core.util.USERNAME_ARG
import dev.linmaung.repo.presentation.GithubRepoViewModel
import dev.linmaung.repo.presentation.UserProfileScreen
import dev.linmaung.user.presentation.HomeScreen

sealed class Screen(val route: String, val title: String) {
    object Main : Screen("main", "main")
    object UserProfile : Screen("user/{$USERNAME_ARG}", "user detail")
}

@Composable
fun MainScreen() {
    // NavController to handle navigation between screens
    val rootNavController = rememberNavController()

    NavHost(
        navController= rootNavController,
        startDestination = Screen.Main.route
    ){
        composable(Screen.Main.route){
            HomeScreen(
                onUserClick = { userName->
                    rootNavController.navigate("user/$userName")
                },
            )
        }

        composable(Screen.UserProfile.route, arguments = listOf(
            navArgument(USERNAME_ARG){ type= NavType.StringType }
        )) { backstackEntry ->
            val githubRepoViewModel: GithubRepoViewModel = hiltViewModel(backstackEntry)
            val username = backstackEntry.arguments?.getString(USERNAME_ARG) ?: ""
            val profileUiState by githubRepoViewModel.profileState.collectAsStateWithLifecycle()
            UserProfileScreen(profileUiState = profileUiState, userName = username) {
                rootNavController.popBackStack()
            }
        }
    }
}

