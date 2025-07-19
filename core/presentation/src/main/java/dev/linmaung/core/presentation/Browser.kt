package dev.linmaung.core.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri


class Browser {
    companion object{
        fun open(context: Context, url: String,onFailure:(String)-> Unit){
            val uri = url.toUri()
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
                    onFailure("No browser available to open this link")

                }
            }
        }

        fun getCustomTabsPackage(context: Context): String? {
            // This returns a Custom Tabs–supporting browser, or null
            return CustomTabsClient.getPackageName(context, null)
        }
    }


}