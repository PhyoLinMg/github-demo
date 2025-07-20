package dev.linmaung.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.linmaung.core.domain.model.GithubRepo

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