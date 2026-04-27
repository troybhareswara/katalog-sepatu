package com.troy.katalog_sepatu

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.troy.katalog_sepatu.screens.BrandListScreen
import com.troy.katalog_sepatu.screens.DetailScreen
import com.troy.katalog_sepatu.screens.HomeScreen
import com.troy.katalog_sepatu.screens.SearchScreen
import com.troy.katalog_sepatu.screens.SortScreen
import com.troy.katalog_sepatu.ui.theme.KatalogsepatuTheme
import com.troy.katalog_sepatu.viewmodel.ShoeViewModel

class MainActivity : ComponentActivity() {

    companion object {
        // Tag Logcat menggunakan NIM
        private const val LOG_TAG = "2055301012"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "Activity started")

        enableEdgeToEdge()
        setContent {
            KatalogsepatuTheme {
                KatalogSepatuApp()
            }
        }
    }

    @Composable
    fun KatalogSepatuApp(
        viewModel: ShoeViewModel = viewModel()
    ) {
        var currentScreen by remember { mutableStateOf("home") }
        var selectedBrand by remember { mutableStateOf("") }
        var selectedShoe by remember { mutableStateOf(com.troy.katalog_sepatu.model.Shoe(0, "", "", "", "")) }

        when (currentScreen) {
            "home" -> HomeScreen(
                onNavigateToBrand = { brand ->
                    Log.d(LOG_TAG, "Navigating to brand: $brand")
                    selectedBrand = brand
                    currentScreen = "brandList"
                },
                onNavigateToSearch = {
                    Log.d(LOG_TAG, "Navigating to search")
                    currentScreen = "search"
                },
                onNavigateToSort = {
                    Log.d(LOG_TAG, "Navigating to sort")
                    currentScreen = "sort"
                }
            )

            "brandList" -> BrandListScreen(
                brand = selectedBrand,
                onBack = {
                    Log.d(LOG_TAG, "Back from brand list")
                    currentScreen = "home"
                },
                onShoeClick = { shoe ->
                    Log.d(LOG_TAG, "Shoe clicked: ${shoe.name}")
                    selectedShoe = shoe
                    currentScreen = "detail"
                }
            )

            "search" -> SearchScreen(
                query = viewModel.searchQuery,
                onQueryChange = { query ->
                    viewModel.updateSearchQuery(query)
                    Log.d(LOG_TAG, "Searching: $query")
                },
                results = viewModel.searchResults,
                errorMessage = viewModel.searchError,
                onClearError = { viewModel.clearSearchError() },
                onShoeClick = { shoe ->
                    Log.d(LOG_TAG, "Shoe clicked from search: ${shoe.name}")
                    selectedShoe = shoe
                    currentScreen = "detail"
                },
                onBack = {
                    Log.d(LOG_TAG, "Back from search")
                    viewModel.resetSearch()
                    currentScreen = "home"
                }
            )

            "sort" -> SortScreen(
                sortedShoes = viewModel.sortedShoes,
                sortOrder = viewModel.sortOrder,
                onSortAZ = {
                    viewModel.bubbleSortAZ()
                    Log.d(LOG_TAG, "Sorting: A-Z")
                },
                onSortZA = {
                    viewModel.bubbleSortZA()
                    Log.d(LOG_TAG, "Sorting: Z-A")
                },
                onShoeClick = { shoe ->
                    Log.d(LOG_TAG, "Shoe clicked from sort: ${shoe.name}")
                    selectedShoe = shoe
                    currentScreen = "detail"
                },
                onBack = {
                    Log.d(LOG_TAG, "Back from sort")
                    viewModel.resetSort()
                    currentScreen = "home"
                }
            )

            "detail" -> DetailScreen(
                shoe = selectedShoe,
                onBack = {
                    Log.d(LOG_TAG, "Back from detail")
                    currentScreen = "home"
                }
            )
        }
    }
}
