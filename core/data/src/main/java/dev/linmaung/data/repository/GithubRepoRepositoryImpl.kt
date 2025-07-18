package dev.linmaung.data.repository

import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.core.domain.repository.GithubRepoRepository
import dev.linmaung.core.util.Result
import dev.linmaung.data.mappers.toGithubRepo
import dev.linmaung.data.source.ProfileDataSource
import jakarta.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
): GithubRepoRepository {
    override suspend fun getRepos(userName: String, page:Int, perPage:Int): Result<List<GithubRepo>> {
        return try {
            val response= profileDataSource.getRepo(userName, page, perPage)
            Result.Success(response.filter { it.fork==false }.map { it.toGithubRepo() })
        }catch (e: Exception){
            Result.Error(e)
        }
    }
}