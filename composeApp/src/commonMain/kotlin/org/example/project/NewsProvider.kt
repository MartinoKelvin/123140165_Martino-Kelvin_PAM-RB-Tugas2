package org.example.project

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

data class News(val title: String, val category: String, val content: String)

fun newsFlow(): Flow<News> = flow {
    val newsList = listOf(
        News("Kotlin 2.0 Released", "Tech", "Details..."),
        News("New Park Opened", "Local", "A new park..."),
        News("AI in 2026", "Tech", "Future of AI...")
    )

    while(true) {
        for (news in newsList) {
            delay(2000) // Delay 2 detik sesuai tugas [cite: 586]
            emit(news)  // Memancarkan data [cite: 343]
        }
    }
}

fun getFilterNews() = newsFlow()
    .filter { it.category == "Tech" }
    .map { "[NEWS-FEED] ${it.title}  (${it.category})" }