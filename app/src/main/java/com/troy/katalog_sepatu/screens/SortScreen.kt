package com.troy.katalog_sepatu.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.troy.katalog_sepatu.model.Shoe
import com.troy.katalog_sepatu.ui.theme.Black
import com.troy.katalog_sepatu.ui.theme.DarkGray
import com.troy.katalog_sepatu.ui.theme.MediumGray
import com.troy.katalog_sepatu.ui.theme.NikeRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortScreen(
    sortedShoes: List<Shoe>,
    sortOrder: String, // "AZ" or "ZA"
    onSortAZ: () -> Unit,
    onSortZA: () -> Unit,
    onShoeClick: (Shoe) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        modifier = modifier,
        containerColor = Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pengurutan",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Sort buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onSortAZ,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (sortOrder == "AZ") NikeRed else DarkGray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "A - Z",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    onClick = onSortZA,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (sortOrder == "ZA") NikeRed else DarkGray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Z - A",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = when (sortOrder) {
                    "AZ" -> "Diurutkan: A - Z"
                    "ZA" -> "Diurutkan: Z - A"
                    else -> "Tekan tombol untuk mengurutkan"
                },
                color = MediumGray,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isLandscape) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sortedShoes) { shoe ->
                        ShoeCard(
                            shoe = shoe,
                            onClick = { onShoeClick(shoe) }
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sortedShoes.size) { index ->
                        ShoeCard(
                            shoe = sortedShoes[index],
                            onClick = { onShoeClick(sortedShoes[index]) }
                        )
                    }
                }
            }
        }
    }
}
