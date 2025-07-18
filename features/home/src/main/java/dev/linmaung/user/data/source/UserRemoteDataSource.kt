package dev.linmaung.user.data.source

import dev.linmaung.data.dto.UserDto
import dev.linmaung.user.data.dto.SearchUserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import jakarta.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getUsers(since: Int): List<UserDto> =
        client.get("users?since=$since").body()

    suspend fun searchUsers(query: String, perPage: Int, page: Int): SearchUserDto =
        client.get("search/users?q=$query&per_page=$perPage&page=$page").body()

    suspend fun getProfile(): UserDto =
        client.get("user").body()
}
