package dev.linmaung.data.mappers

import dev.linmaung.data.dto.UserDto
import dev.linmaung.core.domain.model.User


fun UserDto.toDomainUser(): User {
    return User(
        id = this.id, // Provide a default or handle null ID appropriately
         // Provide a default or handle null login
        avatarUrl = this.avatarUrl,
        name = this.login,
        fullName = this.login,
        following = this.following,
        followers = this.followers,
        // Map other relevant fields
    )
}

// Converts a list of UserResponseItem (DTOs) to a list of User (Domain Models)
fun List<UserDto>.toDomainUserList(): List<User> {
    return this.map { it.toDomainUser() }
}


