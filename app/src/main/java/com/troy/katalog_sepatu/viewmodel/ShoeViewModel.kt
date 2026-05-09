package com.troy.katalog_sepatu.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.troy.katalog_sepatu.data.ShoeData
import com.troy.katalog_sepatu.model.Shoe

class ShoeViewModel : ViewModel() {

    companion object {
        private const val LOG_TAG = "42430052"
    }

    var searchQuery by mutableStateOf("")
        private set

    var searchResults by mutableStateOf<List<Shoe>>(emptyList())
        private set

    var sortedShoes by mutableStateOf(ShoeData.allShoes.toList())
        private set

    var sortOrder by mutableStateOf("")
        private set

    // Validation states
    var searchError by mutableStateOf<String?>(null)
        private set

    // Error state untuk try-catch
    var hasError by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun updateSearchQuery(query: String) {
        searchQuery = query

        // Validasi if-else: input tidak boleh kosong saat search
        if (query.isBlank()) {
            searchError = "Masukkan kata kunci pencarian"
            searchResults = emptyList()
            return
        }

        searchError = null
        searchResults = linearSearch(query)

        // Validasi if-else: cek apakah hasil pencarian kosong
        if (searchResults.isEmpty()) {
            searchError = "Hasil tidak ditemukan untuk \"$query\""
        }
    }

    fun linearSearch(query: String): List<Shoe> {
        if (query.isBlank()) return emptyList()
        return ShoeData.allShoes.filter { shoe ->
            shoe.name.contains(query, ignoreCase = true) ||
            shoe.brand.contains(query, ignoreCase = true)
        }.toList()
    }

    fun bubbleSortAZ() {
        try {
            // Toggle: jika sudah A-Z, reset ke normal
            if (sortOrder == "AZ") {
                sortOrder = ""
                sortedShoes = ShoeData.allShoes.toList()
                Log.d(LOG_TAG, "Sorting di-reset ke normal")
                return
            }
            sortOrder = "AZ"
            val list = ShoeData.allShoes.toMutableList()
            for (i in 0 until list.size - 1) {
                for (j in 0 until list.size - i - 1) {
                    if (list[j].name.lowercase() > list[j + 1].name.lowercase()) {
                        val temp = list[j]
                        list[j] = list[j + 1]
                        list[j + 1] = temp
                    }
                }
            }
            sortedShoes = list
            hasError = false
            Log.d(LOG_TAG, "Sorting A-Z berhasil")
        } catch (e: Exception) {
            hasError = true
            errorMessage = "Error saat mengurutkan: ${e.message}"
            Log.e(LOG_TAG, "Error sorting A-Z: ${e.message}")
        }
    }

    fun bubbleSortZA() {
        try {
            // Toggle: jika sudah Z-A, reset ke normal
            if (sortOrder == "ZA") {
                sortOrder = ""
                sortedShoes = ShoeData.allShoes.toList()
                Log.d(LOG_TAG, "Sorting di-reset ke normal")
                return
            }
            sortOrder = "ZA"
            val list = ShoeData.allShoes.toMutableList()
            for (i in 0 until list.size - 1) {
                for (j in 0 until list.size - i - 1) {
                    if (list[j].name.lowercase() < list[j + 1].name.lowercase()) {
                        val temp = list[j]
                        list[j] = list[j + 1]
                        list[j + 1] = temp
                    }
                }
            }
            sortedShoes = list
            hasError = false
            Log.d(LOG_TAG, "Sorting Z-A berhasil")
        } catch (e: Exception) {
            hasError = true
            errorMessage = "Error saat mengurutkan: ${e.message}"
            Log.e(LOG_TAG, "Error sorting Z-A: ${e.message}")
        }
    }

    fun resetSearch() {
        searchQuery = ""
        searchResults = emptyList()
        searchError = null
    }

    fun resetSort() {
        sortOrder = ""
        sortedShoes = ShoeData.allShoes.toList()
    }

    fun clearSearchError() {
        searchError = null
    }

    fun clearError() {
        hasError = false
        errorMessage = null
    }
}
