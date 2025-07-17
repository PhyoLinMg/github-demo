package dev.linmaung.user.presentation

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
import dev.linmaung.data.source.GithubRepoDataSource
import dev.linmaung.user.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val githubRepoRepository: GithubRepoRepository,
) : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileState

    private val _username = MutableStateFlow("")

    private val _pagedGithubRepos: Flow<PagingData<GithubRepo>> =
        _username.flatMapLatest { username ->
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { GithubRepoDataSource(githubRepoRepository, username) }
            ).flow.cachedIn(viewModelScope)
        }

    init {
        fetchUsername()
    }

    private fun fetchUsername() {
        viewModelScope.launch {
            _profileState.update { it.copy(isLoading = true) }

            val result = userRepository.getProfile()

            when (result) {
                is Result.Error -> {
                    _profileState.update { it.copy(isLoading = false, error = result.exception.message) }
                }

                is Result.Success -> {
                    val user = result.data
                    _username.update { user.fullName }
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
                            reposList = _pagedGithubRepos,
                        )
                    }
                }
            }

        }
    }
}