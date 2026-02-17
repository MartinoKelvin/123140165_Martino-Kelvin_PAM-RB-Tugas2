package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import org.jetbrains.compose.resources.painterResource

import pertemuan2.composeapp.generated.resources.Res

import pertemuan2.composeapp.generated.resources.profil



@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = remember { NewsViewModel() }

        var currentNews by remember { mutableStateOf("Menunggu Berita...") }
        var newsDetail by remember { mutableStateOf(" ") }

        val readCount by viewModel.readCount.collectAsState()

        LaunchedEffect(Unit){
            getFilterNews()
                .collect { newsString ->
                    currentNews = newsString
                    viewModel.incrementRead()

                    newsDetail = "Mengambil detail..."
                    val detailResult = async { viewModel.getNewsDetail(newsString) }
                    newsDetail = detailResult.await()
                }
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Martino News Provider",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "News Feed Simulator",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Menampilkan berita hasil Flow
            Text(text = currentNews, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)

            // Menampilkan detail hasil Async Coroutine
            Text(text = newsDetail, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(16.dp))

            // Menampilkan jumlah dari StateFlow
            Text(
                text = "Berita dibaca: $readCount",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}