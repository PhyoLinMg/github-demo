@file:OptIn(InternalSerializationApi::class)
package dev.linmaung.data.dto


import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("followers_url")
    val followersUrl: String? = "",
    @SerialName("following_url")
    val followingUrl: String? = "",
    @SerialName("gists_url")
    val gistsUrl: String? = "",
    @SerialName("gravatar_id")
    val gravatarId: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("login")
    val login: String? = "",
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("organizations_url")
    val organizationsUrl: String? = "",
    @SerialName("received_events_url")
    val receivedEventsUrl: String? = "",
    @SerialName("repos_url")
    val reposUrl: String? = "",
    @SerialName("site_admin")
    val siteAdmin: Boolean? = false,
    @SerialName("starred_url")
    val starredUrl: String? = "",
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String? = "",
    @SerialName("type")
    val type: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("user_view_type")
    val userViewType: String? = ""
)