package dev.linmaung.user.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import dev.linmaung.core.presentation.ProfileUiState
import dev.linmaung.core.presentation.UserProfileComponent
import dev.linmaung.core.presentation.Browser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(
    profileUiState: ProfileUiState,

    ) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (profileUiState.isLoading) CircularProgressIndicator()
        else {
            UserProfileComponent(profile = profileUiState, onRepoClick = {
                Browser.open(context, it) {
                    Toast.makeText(
                        context, "No browser available to open this link", Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}