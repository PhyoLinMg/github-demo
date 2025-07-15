package dev.linmaung.user.data.repository

import dev.linmaung.user.domain.model.User
import dev.linmaung.user.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): UserRepository {
    override suspend fun getUsers(since: Long): Result<List<User>> {
        val response= httpClient.get("")
    }
}