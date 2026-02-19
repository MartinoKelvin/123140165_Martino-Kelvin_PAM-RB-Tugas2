package org.example.project

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
// Import ini untuk mendapatkan waktu sekarang
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class News(val title: String, val category: String, val content: String, val time: String)

fun newsFlow(): Flow<News> = flow {
    val newsList = listOf(
        News("Kotlin 2.0 Released", "Tech", "Details about Kotlin 2.0...", ""),
        News("New Park Opened", "Local", "A new park in Lampung...", ""),
        News("AI in 2026", "Tech", "Future of AI is here...", ""),
        News("Debat Publik Jadi Sorotan", "Politics", "Berita politik terbaru...", "")
    )

    while(true) {
        for (news in newsList) {
            delay(2000) // Jeda 2 detik sesuai tugas

            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

            val timestamp = "${now.hour.toString().padStart(2, '0')}:" +
                    "${now.minute.toString().padStart(2, '0')}:" +
                    "${now.second.toString().padStart(2, '0')}"

            emit(news.copy(time = timestamp))
        }
    }
}

fun getFilterNews(): Flow<News> = newsFlow()