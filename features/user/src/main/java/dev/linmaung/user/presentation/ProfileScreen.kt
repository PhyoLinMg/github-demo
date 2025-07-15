package dev.linmaung.user.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    user: User
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        // Profile Content
        LazyColumn(
            modifier = Modifier.weight(1f).padding(top = 16.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // Profile Avatar
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = "${user.name}'s avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF0F0F0)),
                    contentScale = ContentScale.Crop,
                    fallback = painterResource(android.R.drawable.ic_menu_gallery)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Name
                Text(
                    text = user.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Username
                Text(
                    text = "@${user.username}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Joined year
                Text(
                    text = "Joined in ${user.joinedYear}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Followers and Following
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = user.followers.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Followers",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = user.following.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Following",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Repositories Section
                Text(
                    text = "Repositories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            items(user.repositories) { repository ->
                RepositoryItem(repository = repository)
            }
        }
    }
}

@Composable
fun RepositoryItem(repository: Repository) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = repository.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Filled.Star, contentDescription = "Stars", tint = Color.Yellow)

                Text(
                    text = repository.stars.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )

            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = repository.description,
            fontSize = 14.sp,
            color = Color.Gray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Tag(text=repository.language)

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(color = Color(0xFFE5E5E5))
    }
}
@Composable
fun Tag(text: String) {
    Surface(
        modifier = Modifier.padding(horizontal = 4.dp), // Add spacing around each tag
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
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
