package dev.linmaung.user.data.dto


import dev.linmaung.data.dto.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchUserDto(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean=false,
    @SerialName("items")
    val items: List<UserDto>,
    @SerialName("total_count")
    val totalCount: Int=0
)