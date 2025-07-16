package dev.linmaung.core.presentation

data class ProfileUiState(
    val isLoading: Boolean= false,
    val userUiState: UserUiState,
    val reposList: List<RepoUiState> = emptyList(),
)

data class UserUiState(
    val avatarUrl:String,
    val name:String,
    val userName:String,
    val fullName:String,
    val followers: Int,
    val following: Int,
)

data class RepoUiState(
    val name:String,
    val language:String,
    val repoUrl:String,
    val starCount: String,
    val description: String
)