package dev.linmaung.core.domain.model


data class User(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val fullName: String,
)