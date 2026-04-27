package com.troy.katalog_sepatu.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.troy.katalog_sepatu.components.ShoeCard
import com.troy.katalog_sepatu.data.ShoeData
import com.troy.katalog_sepatu.model.Shoe
import com.troy.katalog_sepatu.ui.theme.Black
import com.troy.katalog_sepatu.ui.theme.DarkGray
import com.troy.katalog_sepatu.ui.theme.MediumGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandListScreen(
    brand: String,
    onBack: () -> Unit,
    onShoeClick: (Shoe) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val shoes = ShoeData.getShoesByBrand(brand)

    val brandDisplayName = when (brand) {
        "Kobe" -> "Kobe Bryant"
        "LeBron" -> "LeBron James"
        "Sabrina" -> "Sabrina Ionescu"
        else -> brand
    }

    Scaffold(
        modifier = modifier,
        containerColor = Black,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = brandDisplayName,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$brandDisplayName Collection",
                            color = MediumGray,
                            fontSize = 12.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkGray
                )
            )
        }
    ) { innerPadding ->
        if (isLandscape) {
            // Landscape: 2 columns grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 16.dp,
                    bottom = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
            ) {
                items(shoes) { shoe ->
                    ShoeCard(
                        shoe = shoe,
                        onClick = { onShoeClick(shoe) }
                    )
                }
            }
        } else {
            // Portrait: single column
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
            ) {
                items(shoes.size) { index ->
                    ShoeCard(
                        shoe = shoes[index],
                        onClick = { onShoeClick(shoes[index]) }
                    )
                }
            }
        }
    }
}
