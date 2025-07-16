package dev.linmaung.data.repository

import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.core.domain.repository.GithubRepoRepository
import dev.linmaung.core.util.Result


import jakarta.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(): GithubRepoRepository {
    override suspend fun getRepos(userName: String): Result<List<GithubRepo>> {
        return Result.Success(data= listOf())
    }

}