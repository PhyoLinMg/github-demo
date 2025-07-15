package dev.linmaung.user.domain.repository

import dev.linmaung.user.domain.model.User

interface UserRepository {
    suspend fun getUsers(since: Long): Result<List<User>>
}