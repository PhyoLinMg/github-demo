package dev.linmaung.user.data.repository

import dev.linmaung.core.util.Result
import dev.linmaung.user.data.dto.SearchUserDto
import dev.linmaung.user.data.dto.UserDto
import dev.linmaung.user.data.mappers.toDomainUser
import dev.linmaung.user.domain.model.User
import dev.linmaung.user.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): UserRepository {
    override suspend fun getUsers(since: Int): Result<List<User>> {
        return try {
            val response = httpClient.get("users?since=$since").body<List<UserDto>>()
            Result.Success(response.map { it.toDomainUser() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun searchUser(
        query: String,
        perPage: Int,
        page: Int
    ): Result<List<User>> {
        return try {
            val response = httpClient.get("search/users?q=$query&per_page=$perPage&page=$page").body<SearchUserDto>()
            Result.Success(response.items.map { it.toDomainUser() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getProfile(userName: String): Result<User> {
        return try {
            val response = httpClient.get("user").body<UserDto>()
            Result.Success(response.toDomainUser())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}