package dev.linmaung.repo.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import dev.linmaung.core.presentation.Browser
import dev.linmaung.core.presentation.ProfileUiState
import dev.linmaung.core.presentation.UserProfileComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    userName:String="",
    profileUiState: ProfileUiState,
    onBackClick: () -> Unit,
) {
    val context= LocalContext.current

    Scaffold(
        modifier= modifier,
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = onBackClick, content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.Gray
                    )
                })  },
                title = { Text(userName) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        UserProfileComponent(profileUiState,modifier= Modifier.padding(paddingValues)) {
            Browser.open(context, it){
                 Toast.makeText(context, "No browser available to open this link", Toast.LENGTH_LONG).show()
            }
        }
    }
}