package dev.linmaung.user.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class User(
    val id: Long,
    val name: String,
    val avatarUrl: String,
    val bio: String,
    val username: String = "",
    val joinedYear: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val repositories: List<Repository> = emptyList()
)

data class Repository(
    val name: String,
    val description: String,
    val language: String,
    val stars: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSearchScreen(
    users: List<User>,
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onUserClick: (User) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Search Input

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search users or repositories") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") },) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Gray
                        )
                    }
                }
            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF007AFF),
                unfocusedBorderColor = Color(0xFFE5E5E5)
            )
        )

        // User List
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(users) { user ->
                UserItem(
                    user = user,
                    onClick = { onUserClick(user) }
                )
            }
        }


    }
}

@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = "${user.name}'s avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            fallback = painterResource(android.R.drawable.ic_menu_gallery)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // User Name
        Text(
            text = user.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}



@Preview(showBackground = true)
@Composable
fun UserSearchScreenPreview() {
    val sampleUsers = listOf(
        User(
            id = 1,
            name = "Ethan Carter",
            avatarUrl = "https://example.com/avatar1.jpg",
            bio = "Software Engineer"
        ),
        User(
            id = 2,
            name = "Sophia Bennett",
            avatarUrl = "https://example.com/avatar2.jpg",
            bio = "Product Designer"
        ),
        User(
            id = 3,
            name = "Liam Harper",
            avatarUrl = "https://example.com/avatar3.jpg",
            bio = "Data Scientist"
        ),
        User(
            id = 4,
            name = "Olivia Foster",
            avatarUrl = "https://example.com/avatar4.jpg",
            bio = "Mobile Developer"
        ),
        User(
            id = 5,
            name = "Noah Parker",
            avatarUrl = "https://example.com/avatar5.jpg",
            bio = "Web Developer"
        ),
        User(
            id = 6,
            name = "Ava Mitchell",
            avatarUrl = "https://example.com/avatar6.jpg",
            bio = "UI/UX Designer"
        )
    )

    UserSearchScreen(
        users = sampleUsers,
        searchQuery = "",
        onSearchQueryChange = {},
        onUserClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    val sampleUser = User(
        id = 1,
        name = "Ethan Carter",
        username = "ethan_carter",
        avatarUrl = "https://example.com/avatar1.jpg",
        bio = "Software Engineer",
        joinedYear = "2018",
        followers = 12,
        following = 25,
        repositories = listOf(
            Repository(
                name = "finance-manager",
                description = "A web application for managing personal finances, including budgeting, expense tracking, and financial goal setting.",
                language = "JavaScript",
                stars = 103
            ),
            Repository(
                name = "stock-predictor",
                description = "A machine learning project for predicting stock prices based on historical data and market trends.",
                language = "Python",
                stars = 456
            ),
            Repository(
                name = "fitness-tracker",
                description = "An Android app for tracking daily fitness activities, including workouts, steps, and calories burned.",
                language = "Java",
                stars = 789
            )
        )
    )

    UserProfileScreen(
        user = sampleUser
    )
}