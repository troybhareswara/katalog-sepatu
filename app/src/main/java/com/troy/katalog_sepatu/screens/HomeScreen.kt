package com.troy.katalog_sepatu.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.troy.katalog_sepatu.R
import com.troy.katalog_sepatu.components.ShoeCard
import com.troy.katalog_sepatu.model.Shoe
import com.troy.katalog_sepatu.ui.theme.KatalogsepatuTheme
import com.troy.katalog_sepatu.ui.theme.NikeRed
import com.troy.katalog_sepatu.ui.theme.OffWhite
import com.troy.katalog_sepatu.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchResults: List<Shoe>,
    sortedShoes: List<Shoe>,
    sortOrder: String,
    onSortAZ: () -> Unit,
    onSortZA: () -> Unit,
    onNavigateToBrand: (String) -> Unit,
    onShoeClick: (Shoe) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Determine which mode is active
    val isSearchMode = searchQuery.isNotEmpty()
    val isSortMode = sortOrder.isNotEmpty()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nike_logo),
                contentDescription = "Nike Logo",
                modifier = Modifier.height(48.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "BASKETBALL COLLECTION",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 3.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    onSearchQueryChange(newValue)
                },
                placeholder = {
                    Text(
                        text = "Cari nama sepatu atau merk...",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                onSearchQueryChange("")
                                focusManager.clearFocus()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = NikeRed,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = NikeRed,
                    focusedContainerColor = Color(0xFF1A1A1A),
                    unfocusedContainerColor = Color(0xFF1A1A1A)
                ),
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )
            )
        }

        // Content Section
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            // Section Title - Aktifkan Mode
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "AKTIFKAN MODE",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    // Mode Indicator
                    AnimatedVisibility(visible = isSearchMode || isSortMode) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isSearchMode) {
                                Text(
                                    text = "🔍 Pencarian",
                                    color = NikeRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            if (isSortMode) {
                                Text(
                                    text = "📋 Sort",
                                    color = NikeRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // Sort Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            onSortAZ()
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (sortOrder == "AZ") NikeRed else Color(0xFFE0E0E0)
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = if (sortOrder == "AZ") 4.dp else 1.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = if (sortOrder == "AZ") Color.White else Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "A - Z",
                            color = if (sortOrder == "AZ") Color.White else Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = {
                            onSortZA()
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (sortOrder == "ZA") NikeRed else Color(0xFFE0E0E0)
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = if (sortOrder == "ZA") 4.dp else 1.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = if (sortOrder == "ZA") Color.White else Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Z - A",
                            color = if (sortOrder == "ZA") Color.White else Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Results Section
            item {
                Spacer(modifier = Modifier.height(16.dp))

                // Header untuk hasil
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when {
                            isSearchMode && searchResults.isNotEmpty() -> "HASIL PENCARIAN"
                            isSortMode -> "HASIL PENGURUTAN"
                            else -> "SEMUA SEPATU"
                        },
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    when {
                        isSearchMode && searchResults.isNotEmpty() -> {
                            Text(
                                text = "${searchResults.size} ditemukan",
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        }
                        isSortMode -> {
                            Text(
                                text = "${sortedShoes.size} sepatu",
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        }
                        else -> {
                            Text(
                                text = "9 sepatu",
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Display Results based on mode
            when {
                // Search Mode - Tampilkan hasil pencarian
                isSearchMode && searchResults.isNotEmpty() -> {
                    items(searchResults.size) { index ->
                        SearchResultItem(
                            shoe = searchResults[index],
                            onClick = { onShoeClick(searchResults[index]) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Search with no results
                isSearchMode && searchResults.isEmpty() -> {
                    item {
                        NoResultsCard(searchQuery = searchQuery)
                    }
                }

                // Sort Mode - Tampilkan hasil sorting
                isSortMode -> {
                    if (isLandscape) {
                        item {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.height(400.dp)
                            ) {
                                items(sortedShoes.size) { index ->
                                    ShoeCard(
                                        shoe = sortedShoes[index],
                                        onClick = { onShoeClick(sortedShoes[index]) }
                                    )
                                }
                            }
                        }
                    } else {
                        items(sortedShoes.size) { index ->
                            ShoeCard(
                                shoe = sortedShoes[index],
                                onClick = { onShoeClick(sortedShoes[index]) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                // Default Mode - Tampilkan semua sepatu
                else -> {
                    item {
                        AllShoesList(
                            isLandscape = isLandscape,
                            onShoeClick = onShoeClick
                        )
                    }
                }
            }

            // Divider before brand section (only if not showing shoes already)
            if (isSortMode || (isSearchMode && searchResults.isEmpty())) {
                item {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
            }

            // Brand Section Title
            item {
                Text(
                    text = "PILIH MERK",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(
                        top = if (isSortMode || (isSearchMode && searchResults.isEmpty())) 0.dp else 24.dp,
                        bottom = 16.dp
                    )
                )
            }

            // Brand Cards
            if (isLandscape) {
                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.height(400.dp)
                    ) {
                        item {
                            BrandCardItem(
                                brandName = "Kobe Bryant",
                                initial = "K",
                                logoRes = R.drawable.logo_kobe_bryant,
                                onClick = { onNavigateToBrand("Kobe") }
                            )
                        }
                        item {
                            BrandCardItem(
                                brandName = "LeBron James",
                                initial = "L",
                                logoRes = R.drawable.logo_lebron_james,
                                onClick = { onNavigateToBrand("LeBron") }
                            )
                        }
                        item {
                            BrandCardItem(
                                brandName = "Sabrina Ionescu",
                                initial = "S",
                                logoRes = R.drawable.logo_sabrina,
                                onClick = { onNavigateToBrand("Sabrina") }
                            )
                        }
                    }
                }
            } else {
                item {
                    BrandCardItem(
                        brandName = "Kobe Bryant",
                        initial = "K",
                        logoRes = R.drawable.logo_kobe_bryant,
                        onClick = { onNavigateToBrand("Kobe") }
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    BrandCardItem(
                        brandName = "LeBron James",
                        initial = "L",
                        logoRes = R.drawable.logo_lebron_james,
                        onClick = { onNavigateToBrand("LeBron") }
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    BrandCardItem(
                        brandName = "Sabrina Ionescu",
                        initial = "S",
                        logoRes = R.drawable.logo_sabrina,
                        onClick = { onNavigateToBrand("Sabrina") }
                    )
                }
            }

            // Footer
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Cari | Urutkan",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun AllShoesList(
    isLandscape: Boolean,
    onShoeClick: (Shoe) -> Unit
) {
    val allShoes = com.troy.katalog_sepatu.data.ShoeData.allShoes.toList()

    if (isLandscape) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(400.dp)
        ) {
            items(allShoes.size) { index ->
                ShoeCard(
                    shoe = allShoes[index],
                    onClick = { onShoeClick(allShoes[index]) }
                )
            }
        }
    } else {
        Column {
            allShoes.forEach { shoe ->
                ShoeCard(
                    shoe = shoe,
                    onClick = { onShoeClick(shoe) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun NoResultsCard(searchQuery: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = OffWhite
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tidak ada hasil",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Tidak ditemukan \"$searchQuery\"",
                color = TextSecondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Coba kata kunci lain",
                color = NikeRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun BrandCardItem(
    brandName: String,
    initial: String,
    logoRes: Int = 0,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(NikeRed),
                contentAlignment = Alignment.Center
            ) {
                if (logoRes != 0) {
                    Image(
                        painter = painterResource(id = logoRes),
                        contentDescription = brandName,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(28.dp)),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(
                        text = initial,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = brandName,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "3 Sepatu",
                color = TextSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun SearchResultItem(
    shoe: Shoe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = OffWhite
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Shoe Image or Placeholder
            if (shoe.imageRes != 0) {
                Image(
                    painter = painterResource(id = shoe.imageRes),
                    contentDescription = shoe.name,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEEEEEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = shoe.brand.take(2).uppercase(),
                        color = NikeRed,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = shoe.name,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = shoe.brand,
                    color = NikeRed,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = shoe.price,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(name = "HomeScreen Portrait", showBackground = true)
@Composable
fun HomeScreenPreview() {
    KatalogsepatuTheme {
        HomeScreen(
            searchQuery = "",
            onSearchQueryChange = {},
            searchResults = emptyList(),
            sortedShoes = com.troy.katalog_sepatu.data.ShoeData.allShoes.toList(),
            sortOrder = "",
            onSortAZ = {},
            onSortZA = {},
            onNavigateToBrand = {},
            onShoeClick = {}
        )
    }
}