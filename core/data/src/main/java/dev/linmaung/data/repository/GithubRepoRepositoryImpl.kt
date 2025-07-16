package dev.linmaung.data.repository

import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.core.domain.repository.GithubRepoRepository
import dev.linmaung.core.util.Result
import dev.linmaung.data.dto.RepoItemDto
import dev.linmaung.data.mappers.toGithubRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


import jakarta.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): GithubRepoRepository {
    override suspend fun getRepos(userName: String, page:Int, perPage:Int): Result<List<GithubRepo>> {
        return try {
            val response= httpClient.get("users/$userName/repos?page=$page&per_page=$perPage").body<List<RepoItemDto>>()
            Result.Success(response.map { it.toGithubRepo() })
        }catch (e: Exception){
            Result.Error(e)
        }
    }

}