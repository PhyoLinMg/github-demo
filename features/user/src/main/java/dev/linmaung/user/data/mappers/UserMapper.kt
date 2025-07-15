
package dev.linmaung.user.data.mappers

import dev.linmaung.user.data.dto.UserResponseItem
import dev.linmaung.user.domain.model.User


fun UserResponseItem.toDomainUser(): User {
    return User(
        id = this.id, // Provide a default or handle null ID appropriately
         // Provide a default or handle null login
        avatarUrl = this.avatarUrl,
        name = this.login,
        // Map other relevant fields
    )
}

// Converts a list of UserResponseItem (DTOs) to a list of User (Domain Models)
fun List<UserResponseItem>.toDomainUserList(): List<User> {
    return this.map { it.toDomainUser() }
}


