package com.troy.katalog_sepatu.model

data class Shoe(
    val id: Int,
    val name: String,
    val brand: String,
    val price: String,
    val description: String,
    val colorOption: String = "Hitam",
    val imageRes: Int = 0  // 0 = placeholder, >0 = resource ID gambar
)