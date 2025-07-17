package dev.linmaung.repo.data.repository

import dev.linmaung.core.domain.model.User
import dev.linmaung.data.dto.UserDto
import dev.linmaung.data.mappers.toDomainUser
import dev.linmaung.repo.domain.repository.UserProfileRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import dev.linmaung.core.util.Result
import io.ktor.client.request.get
import jakarta.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): UserProfileRepository {
    override suspend fun getUserProfile(userName: String): Result<User> {
        return try {
            val response= httpClient.get("users/$userName").body<UserDto>()
            Result.Success(data= response.toDomainUser())
        }catch(e: Exception){
            Result.Error(e)
        }
    }
}