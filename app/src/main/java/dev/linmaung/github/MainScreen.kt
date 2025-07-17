package dev.linmaung.github

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.linmaung.repo.presentation.GithubRepoViewModel
import dev.linmaung.repo.presentation.UserProfileScreen
import dev.linmaung.user.presentation.HomeScreen


@Composable
fun MainScreen() {
    // NavController to handle navigation between screens
    val rootNavController = rememberNavController()

    NavHost(
        navController= rootNavController,
        startDestination = "main"
    ){
        composable("main"){
            HomeScreen(
                onUserClick = { userName->
                    rootNavController.navigate("user/$userName")
                },
            )
        }

        composable("user/{username}", arguments = listOf(
            navArgument("username"){ type= NavType.StringType }
        )) { backstackEntry ->
            val githubRepoViewModel: GithubRepoViewModel = hiltViewModel(backstackEntry)
            val username = backstackEntry.arguments?.getString("username") ?: ""
            val profileUiState by githubRepoViewModel.profileState.collectAsStateWithLifecycle()
            UserProfileScreen(profileUiState = profileUiState, userName = username) {
                rootNavController.popBackStack()
            }
        }
    }
}

