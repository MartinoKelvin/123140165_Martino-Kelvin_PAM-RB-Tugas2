package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = remember { NewsViewModel() }

        val newsList = remember { mutableStateListOf<News>() }

        var searchQuery by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf("All") }
        val categories = listOf("All", "Tech", "Local", "Politics")

        val readCount by viewModel.readCount.collectAsState()

        LaunchedEffect(Unit) {
            getFilterNews().collect { news ->
                newsList.add(0, news)
            }
        }

        val displayedNews = newsList.filter {
            (selectedCategory == "All" || it.category == selectedCategory) &&
                    it.title.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    text = "Martino News Provider",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = "News Feed Simulator", style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Cari berita...") },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = "Total Berita Dibaca: $readCount",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(displayedNews) { news ->
                var detailText by remember { mutableStateOf("Klik detail untuk membaca...") }
                val scope = rememberCoroutineScope()

                NewsCard(
                    news = news,
                    detailText = detailText,
                    onDetailClick = {
                        viewModel.incrementRead()

                        scope.launch {
                            detailText = "Sedang mengambil konten..."
                            detailText = viewModel.getNewsDetail(news.title)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun NewsCard(news: News, detailText: String, onDetailClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(0.9f),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = news.category,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelMedium
                )

                Text(text = news.time, style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = detailText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )

            Button(
                onClick = onDetailClick,
                modifier = Modifier.align(Alignment.End).padding(top = 8.dp)
            ) {
                Text("Baca detail")
            }
        }
    }
}