package dev.linmaung.user.data.repository

import dev.linmaung.core.util.Result
import dev.linmaung.data.mappers.toDomainUser
import dev.linmaung.core.domain.model.User
import dev.linmaung.user.data.source.UserRemoteDataSource
import dev.linmaung.user.domain.repository.UserRepository
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource

): UserRepository {
    override suspend fun getUsers(since: Int): Result<List<User>> {
        return try {
            val response = userRemoteDataSource.getUsers(since)
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
            val response = userRemoteDataSource.searchUsers(query,perPage,page)
            Result.Success(response.items.map { it.toDomainUser() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getProfile(): Result<User> {
        return try {
            val response = userRemoteDataSource.getProfile()
            Result.Success(response.toDomainUser())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}