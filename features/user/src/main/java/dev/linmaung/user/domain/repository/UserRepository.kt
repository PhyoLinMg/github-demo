package dev.linmaung.user.domain.repository

import dev.linmaung.core.util.Result
import dev.linmaung.user.domain.model.User

interface UserRepository {
    suspend fun getUsers(since: Int): Result<List<User>>

    suspend fun searchUser(query: String): Result<List<User>>
}