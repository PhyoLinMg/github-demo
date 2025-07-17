package dev.linmaung.user.domain.repository

import dev.linmaung.core.util.Result
import dev.linmaung.core.domain.model.User

interface UserRepository {
    suspend fun getUsers(since: Int): Result<List<User>>

    suspend fun searchUser(query: String,perPage:Int=20, page:Int=1): Result<List<User>>

    suspend fun getProfile(): Result<User>
}