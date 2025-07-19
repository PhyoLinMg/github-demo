package dev.linmaung.user.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems


sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}
val items = listOf(
    Screen.Home,
    Screen.Profile
)
@Composable
fun HomeScreen(
    onUserClick: (String) -> Unit,

) {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        NavigationBar(
            containerColor = Color.Black
        ) {
            // Get the current back stack entry to determine the current route
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Iterate through the list of screens to create BottomNavigationItems
            items.forEach { screen ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(

                    ),
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
                            /* Restore state when reselecting a previously selected item */
                            restoreState = true
                        }
                    }
                )
            }
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ){
            composable(Screen.Home.route) {
                val viewModel: UserViewModel = hiltViewModel()
                val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

                val allUsersPagingData = viewModel.allUsersPagingData.collectAsLazyPagingItems()
                val searchUsersPagingData = viewModel.searchUsersPagingData.collectAsLazyPagingItems()
                UserSearchScreen(
                    allUsersPagingData = allUsersPagingData,
                    searchUsersPagingData = searchUsersPagingData,
                    searchQuery = searchQuery,
                    setSearchQuery = viewModel::setSearchQuery,
                    onUserClick = onUserClick
                )
            }
            composable(Screen.Profile.route) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val profileState by profileViewModel.profileState.collectAsStateWithLifecycle()

                MyProfileScreen(profileState)
            }
        }
    }
}
