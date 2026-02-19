package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext


class NewsViewModel {
    // Step 4: StateFlow untuk manajemen state jumlah baca [cite: 502, 589]
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    fun incrementRead() {
        _readCount.value++
    }

    suspend fun getNewsDetail(title: String): String = withContext(Dispatchers.IO) {
        delay(1000)
        "Detail konten lengkap untuk berita: $title. Ini diambil secara async agar UI tetap responsif."
    }
}