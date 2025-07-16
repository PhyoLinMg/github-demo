package dev.linmaung.repo.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.linmaung.core.domain.repository.GithubRepoRepository
import jakarta.inject.Inject


@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val githubRepoRepository: GithubRepoRepository
): ViewModel() {

}