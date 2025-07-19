package dev.linmaung.repo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.core.domain.repository.GithubRepoRepository
import dev.linmaung.core.presentation.ProfileUiState
import dev.linmaung.core.presentation.UserUiState
import dev.linmaung.core.util.Result
import dev.linmaung.core.util.USERNAME_ARG
import dev.linmaung.data.source.GithubRepoDataSource
import dev.linmaung.repo.domain.repository.UserProfileRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val githubRepoRepository: GithubRepoRepository,
    private val userProfileRepository: UserProfileRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val userName: String = checkNotNull(savedStateHandle[USERNAME_ARG])

    private val _profileState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileState

    private val _pagedGithubRepos: Flow<PagingData<GithubRepo>> = Pager(
        config = PagingConfig(
            pageSize = 20, enablePlaceholders = true
        ),
        pagingSourceFactory = {
            GithubRepoDataSource(
                githubRepoRepository,
                userName
            )
        }).flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            _profileState.value = profileState.value.copy(
                reposList = _pagedGithubRepos
            )
        }
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _profileState.update { it.copy(isLoading = true) }

            val result = userProfileRepository.getUserProfile(userName)
            when (result) {
                is Result.Success -> {
                    val user = result.data
                    _profileState.update {
                        it.copy(
                            isLoading = false,
                            userUiState = UserUiState(
                                avatarUrl = user.avatarUrl,
                                name = user.name,
                                userName = user.fullName,
                                fullName = user.fullName,
                                followers = user.followers,
                                following = user.following
                            ),
                        )
                    }


                }

                is Result.Error -> {
                    _profileState.update { it.copy(isLoading = false) }
                }
            }
        }
    }


}