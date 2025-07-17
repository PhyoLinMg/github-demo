package dev.linmaung.repo.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
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
        UserProfileComponent(profileUiState,modifier= Modifier.padding(paddingValues), onRetry = {}) {
            val uri = it.toUri()
            val customTabsPackage = getCustomTabsPackage(context)

            if (customTabsPackage != null) {
                val intent= CustomTabsIntent.Builder()
                    .setShowTitle(true)
                    .build()
                    .intent
                    .apply {
                        setPackage(customTabsPackage)
                    }
                context.startActivity(intent.apply { data= uri })
            } else {
                // Fallback: check for generic browsers
                val viewIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                try {
                    context.startActivity(viewIntent)
                } catch (e: ActivityNotFoundException) {
                    // No app: notify user
                    Toast.makeText(context, "No browser available to open this link", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
fun getCustomTabsPackage(context: Context): String? {
    // This returns a Custom Tabs–supporting browser, or null
    return CustomTabsClient.getPackageName(context, null)
}