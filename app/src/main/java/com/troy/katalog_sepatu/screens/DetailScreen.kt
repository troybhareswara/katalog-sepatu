package com.troy.katalog_sepatu.screens

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.troy.katalog_sepatu.model.Shoe
import com.troy.katalog_sepatu.ui.theme.Black
import com.troy.katalog_sepatu.ui.theme.DarkGray
import com.troy.katalog_sepatu.ui.theme.MediumGray
import com.troy.katalog_sepatu.ui.theme.NikeRed

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
        containerColor = Black,
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
                    containerColor = DarkGray
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
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Image placeholder
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(DarkGray),
                    contentAlignment = Alignment.Center
                ) {
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
            ) {
                // Image placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(DarkGray),
                    contentAlignment = Alignment.Center
                ) {
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
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = shoe.price,
        color = Color.White,
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
        color = Color.White.copy(alpha = 0.8f),
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
        color = Color.White,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(32.dp))
}
