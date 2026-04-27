package com.troy.katalog_sepatu.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.troy.katalog_sepatu.components.BrandCard
import com.troy.katalog_sepatu.ui.theme.Black
import com.troy.katalog_sepatu.ui.theme.MediumGray
import com.troy.katalog_sepatu.ui.theme.NikeRed
import com.troy.katalog_sepatu.ui.theme.SurfaceBlack

@Composable
fun HomeScreen(
    onNavigateToBrand: (String) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToSort: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val brands = listOf(
        Triple("Kobe", "Kobe Bryant", 3),
        Triple("LeBron", "LeBron James", 3),
        Triple("Sabrina", "Sabrina Ionescu", 3)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Header
        Text(
            text = "NIKE",
            color = NikeRed,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 8.sp
        )

        Text(
            text = "BASKETBALL",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 4.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Katalog Sepatu Basket",
            color = MediumGray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "PILIH MERK",
            color = MediumGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLandscape) {
            // Landscape: 2 columns
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    BrandCard(
                        brandName = "Kobe Bryant",
                        shoeCount = 3,
                        onClick = { onNavigateToBrand("Kobe") }
                    )
                }
                item {
                    BrandCard(
                        brandName = "LeBron James",
                        shoeCount = 3,
                        onClick = { onNavigateToBrand("LeBron") }
                    )
                }
                item {
                    BrandCard(
                        brandName = "Sabrina Ionescu",
                        shoeCount = 3,
                        onClick = { onNavigateToBrand("Sabrina") }
                    )
                }
            }
        } else {
            // Portrait: single column
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    BrandCard(
                        brandName = "Kobe Bryant",
                        shoeCount = 3,
                        onClick = { onNavigateToBrand("Kobe") }
                    )
                }
                item {
                    BrandCard(
                        brandName = "LeBron James",
                        shoeCount = 3,
                        onClick = { onNavigateToBrand("LeBron") }
                    )
                }
                item {
                    BrandCard(
                        brandName = "Sabrina Ionescu",
                        shoeCount = 3,
                        onClick = { onNavigateToBrand("Sabrina") }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom navigation
        Text(
            text = "Cari | Urutkan",
            color = MediumGray,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
