package dev.linmaung.user.presentation


import android.R.attr.data
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
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
import androidx.core.net.toUri


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(
    profileUiState: ProfileUiState,

) {
    val context= LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
       if(profileUiState.isLoading) CircularProgressIndicator()
        else {
           UserProfileComponent(profile= profileUiState, onRetry = {}, onRepoClick = {
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
           })
       }
    }
}
fun getCustomTabsPackage(context: Context): String? {
    // This returns a Custom Tabs–supporting browser, or null
    return CustomTabsClient.getPackageName(context, null)
}

