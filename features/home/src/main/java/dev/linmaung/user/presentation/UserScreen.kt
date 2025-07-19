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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import dev.linmaung.core.domain.model.User
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSearchScreen(
    onUserClick: (String) -> Unit = {},
    allUsersPagingData: LazyPagingItems<User>,
    searchUsersPagingData: LazyPagingItems<User>,
    searchQuery: String,
    setSearchQuery: (String) -> Unit,
) {
    val isSearching = searchQuery.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Search Input
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                setSearchQuery(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search users or repositories") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { setSearchQuery("") }) {
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
                focusedBorderColor = Color(0xFF007AFF), unfocusedBorderColor = Color(0xFFE5E5E5)
            )
        )

        // User List
        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            val currentPagingItems = if (isSearching) searchUsersPagingData else allUsersPagingData


            items(
                currentPagingItems.itemCount, key = currentPagingItems.itemKey { it.id }) { index ->
                val user = currentPagingItems[index]
                if (user != null) UserItem(user) {
                    onUserClick(user.name)
                }
            }

            when {
                currentPagingItems.loadState.refresh is LoadState.Loading || currentPagingItems.loadState.append is LoadState.Loading -> {
                    item(
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                currentPagingItems.loadState.refresh is LoadState.Error -> {
                    val error = currentPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            error = error.error.message ?: "Unknown error",
                            onRetry = { currentPagingItems.refresh() })
                    }
                }
            }
        }


    }
}

@Composable
fun ErrorItem(
    error: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
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
            text = error, fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRetry, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0969da)
            )
        ) {
            Text(
                text = "Try Again", color = Color.White
            )
        }
    }
}

@Composable
fun UserItem(
    user: User, onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
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
    // Create dummy PagingData for preview
    val dummyUsers = List(10) { index ->
        User(
            id = index,
            name = "User $index",
            avatarUrl = "https://example.com/avatar.png",
            followers = 0,
            following = 1,
            fullName = "User $index"
        )
    }
    val allUsersPagingData = flowOf(PagingData.from(dummyUsers)).collectAsLazyPagingItems()
    val searchUsersPagingData = kotlinx.coroutines.flow.flowOf(PagingData.from(dummyUsers.filter {
        it.name.contains(
            "user 0", ignoreCase = true
        )
    })).collectAsLazyPagingItems()

    UserSearchScreen(
        allUsersPagingData = allUsersPagingData,
        searchUsersPagingData = searchUsersPagingData,
        searchQuery = "",
        setSearchQuery = {
            // In a real scenario, this would trigger a new search and update searchUsersPagingData
        },
        onUserClick = { userName ->
            // Handle user click for preview if needed
        })
}

