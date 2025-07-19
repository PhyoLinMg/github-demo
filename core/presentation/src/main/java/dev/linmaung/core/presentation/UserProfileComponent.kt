package dev.linmaung.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import dev.linmaung.core.domain.model.GithubRepo

@Composable
fun UserProfileComponent(
    profile: ProfileUiState,
    modifier: Modifier = Modifier,
    onRepoClick: (String) -> Unit,

) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
            when {
                profile.error != null -> {
                    ErrorStateComponent(
                        error = profile.error,
                        onRetry = {  },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                profile.isLoading && profile.userUiState == null -> {
                    LoadingStateComponent(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {
                    ProfileContentComponent(
                        profile = profile,
                        onRepoClick = onRepoClick,
                        onRetry = {  },
                        modifier = Modifier.fillMaxSize()
                    )
                }

        }
    }
}
@Composable
private fun ErrorStateComponent(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(android.R.drawable.ic_dialog_alert),
            contentDescription = "Error",
            tint = Color.Red,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Something went wrong",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = error,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0969da)
            )
        ) {
            Text(
                text = "Try Again",
                color = Color.White
            )
        }
    }
}

@Composable
private fun LoadingStateComponent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Loading profile...",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
@Composable
private fun ProfileContentComponent(
    profile: ProfileUiState,
    onRepoClick: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
){
    val list = profile.reposList.collectAsLazyPagingItems()
    // Profile Content
    LazyColumn(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Profile Avatar
            AsyncImage(
                model = profile.userUiState?.avatarUrl ?: "",
                contentDescription = "${profile.userUiState?.name}'s avatar",
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
                text = profile.userUiState?.name.orEmpty(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Username
            Text(
                text = "@${profile.userUiState?.userName}",
                fontSize = 16.sp,
                color = Color.Gray
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Followers and Following
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 20.dp, vertical = 15.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = profile.userUiState?.followers.toString(),
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
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 20.dp, vertical = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = profile.userUiState?.following.toString(),
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

        items(list.itemCount) { index ->
            val repository = list[index] ?: return@items
            RepositoryItem(repository = repository, onRepoClick)
        }
        when {
            list.loadState.refresh is LoadState.Loading || list.loadState.append is LoadState.Loading -> {
                item(
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            list.loadState.refresh is LoadState.Error -> {
                val error = list.loadState.refresh as LoadState.Error
                item {
                    ErrorItem(
                        error = error.error.message ?: "Unknown error",
                        onRetry = { list.refresh() })
                }
            }
        }

    }
}

@Composable
fun RepositoryItem(repository: GithubRepo, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(repository.repoUrl)
            }
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
            ) {
                Text(
                    text = repository.starCount.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Stars",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(15.dp)
                )

            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (repository.description.orEmpty().isNotEmpty()) {
            Text(
                text = repository.description ?: "",
                fontSize = 14.sp,
                color = Color(0xFF98acc3),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(repository.language, style = TextStyle(color = Color(0xFF98acc3)))

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(color = Color(0xFFE5E5E5))
    }
}
