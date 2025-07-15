package dev.linmaung.github

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.linmaung.user.presentation.Repository
import dev.linmaung.user.presentation.User
import dev.linmaung.user.presentation.UserProfileScreen
import dev.linmaung.user.presentation.UserSearchScreen

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}

// List of screens to be displayed in the bottom navigation bar
val items = listOf(
    Screen.Home,
    Screen.Profile
)
@Composable
fun MainScreen() {
    // NavController to handle navigation between screens
    val navController = rememberNavController()

    // Scaffold provides a standard layout structure for the app
    Scaffold(
        bottomBar = {
            // BottomNavigation bar composable
            NavigationBar {
                // Get the current back stack entry to determine the current route
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Iterate through the list of screens to create BottomNavigationItems
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            // Navigate to the selected screen
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavigationHost: The container for the navigation graph
        NavigationHost(navController = navController, innerPadding = innerPadding)
    }
}
@Composable
fun NavigationHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        // Composable for the Home screen
        composable(Screen.Home.route) {
            UserSearchScreen(
                users = listOf(
                    User(
                        id = 1,
                        name = "Ethan Carter",
                        avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
                        bio = "Software Engineer"
                    ),
                    User(
                        id = 2,
                        name = "Sophia Bennett",
                        avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
                        bio = "Product Designer"
                    ),
                    User(
                        id = 3,
                        name = "Liam Harper",
                        avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
                        bio = "Data Scientist"
                    ),
                    User(
                        id = 4,
                        name = "Olivia Foster",
                        avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
                        bio = "Mobile Developer"
                    ),
                    User(
                        id = 5,
                        name = "Noah Parker",
                        avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
                        bio = "Web Developer"
                    ),
                    User(
                        id = 6,
                        name = "Ava Mitchell",
                        avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
                        bio = "UI/UX Designer"
                    )
                ),
            )
        }
        // Composable for the Profile screen
        composable(Screen.Profile.route) {
            UserProfileScreen(
                user = User(
                    id = 1,
                    name = "Ethan Carter",
                    username = "ethan_carter",
                    avatarUrl = "https://example.com/avatar1.jpg",
                    bio = "Software Engineer",
                    joinedYear = "2018",
                    followers = 12,
                    following = 25,
                    repositories = listOf(
                        Repository(
                            name = "finance-manager",
                            description = "A web application for managing personal finances, including budgeting, expense tracking, and financial goal setting.",
                            language = "JavaScript",
                            stars = 103
                        ),
                        Repository(
                            name = "stock-predictor",
                            description = "A machine learning project for predicting stock prices based on historical data and market trends.",
                            language = "Python",
                            stars = 456
                        ),
                        Repository(
                            name = "fitness-tracker",
                            description = "An Android app for tracking daily fitness activities, including workouts, steps, and calories burned.",
                            language = "Java",
                            stars = 789
                        )
                    )
                )
            )
        }
    }
}

