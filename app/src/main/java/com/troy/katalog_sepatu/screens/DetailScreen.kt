package com.troy.katalog_sepatu.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.troy.katalog_sepatu.model.Shoe
import com.troy.katalog_sepatu.ui.theme.KatalogsepatuTheme
import com.troy.katalog_sepatu.ui.theme.MediumGray
import com.troy.katalog_sepatu.ui.theme.NikeRed
import com.troy.katalog_sepatu.ui.theme.OffWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    shoe: Shoe,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail",
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
                    containerColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        if (isLandscape) {
            // Landscape layout
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(OffWhite),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Image Section
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    if (shoe.imageRes != 0) {
                        Image(
                            painter = painterResource(id = shoe.imageRes),
                            contentDescription = shoe.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = shoe.brand.take(2).uppercase(),
                                color = NikeRed,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "#${shoe.id}",
                                color = MediumGray,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                // Info
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailContent(shoe = shoe)
                }
            }
        } else {
            // Portrait layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .background(OffWhite)
            ) {
                // Image Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    if (shoe.imageRes != 0) {
                        Image(
                            painter = painterResource(id = shoe.imageRes),
                            contentDescription = shoe.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = shoe.brand.take(2).uppercase(),
                                color = NikeRed,
                                fontSize = 64.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "#${shoe.id}",
                                color = MediumGray,
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                // Info content
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    DetailContent(shoe = shoe)
                }
            }
        }
    }
}

@Composable
private fun DetailContent(shoe: Shoe) {
    Text(
        text = shoe.brand,
        color = NikeRed,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = shoe.name,
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = shoe.price,
        color = Color.Black,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "DESKRIPSI",
        color = MediumGray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 2.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = shoe.description,
        color = Color.Black.copy(alpha = 0.8f),
        fontSize = 14.sp,
        lineHeight = 22.sp
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "WARNA",
        color = MediumGray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 2.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = shoe.colorOption,
        color = Color.Black,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(32.dp))
}

@Preview(name = "DetailScreen Portrait", showBackground = true)
@Composable
fun DetailScreenPreview() {
    KatalogsepatuTheme {
        DetailScreen(
            shoe = com.troy.katalog_sepatu.model.Shoe(
                id = 1,
                name = "Nike Kobe 6 Protro \"Mambacital\"",
                brand = "Kobe",
                price = "Rp 2.800.000",
                description = "Kobe 6 Protro Mambacital merayakan warisan Kobe Bryant dengan desain ikonik.",
                colorOption = "Hitam"
            ),
            onBack = {}
        )
    }
}