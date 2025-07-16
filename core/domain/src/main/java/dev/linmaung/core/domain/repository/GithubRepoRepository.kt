package dev.linmaung.core.domain.repository

import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.core.util.Result


interface GithubRepoRepository {
    suspend fun getRepos(userName: String,page:Int=1, perPage:Int=20): Result<List<GithubRepo>>
}