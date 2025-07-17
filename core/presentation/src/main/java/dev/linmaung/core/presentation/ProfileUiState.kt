package dev.linmaung.core.presentation

import androidx.paging.PagingData
import dev.linmaung.core.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ProfileUiState(
    val isLoading: Boolean= false,
    val userUiState: UserUiState?=null,
    val reposList: Flow<PagingData<GithubRepo>> = flowOf(),
    val error: String?= null
)

data class UserUiState(
    val avatarUrl:String,
    val name:String,
    val userName:String,
    val fullName:String,
    val followers: Int,
    val following: Int,
)

