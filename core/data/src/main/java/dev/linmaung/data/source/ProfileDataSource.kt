package dev.linmaung.data.source


import dev.linmaung.data.dto.RepoItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class ProfileDataSource @Inject constructor(private val httpClient: HttpClient){
    suspend fun getRepo(userName: String, page:Int, perPage:Int): List<RepoItemDto>{
        return httpClient.get("users/$userName/repos?page=$page&per_page=$perPage").body<List<RepoItemDto>>()
    }

}