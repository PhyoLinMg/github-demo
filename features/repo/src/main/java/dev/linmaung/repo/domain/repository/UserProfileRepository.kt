package dev.linmaung.repo.domain.repository

import dev.linmaung.core.domain.model.User
import dev.linmaung.core.util.Result

interface UserProfileRepository {
    suspend fun getUserProfile(userName: String): Result<User>
}