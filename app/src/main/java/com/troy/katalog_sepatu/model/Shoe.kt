package com.troy.katalog_sepatu.model

data class Shoe(
    val id: Int,
    val name: String,
    val brand: String,
    val price: String,
    val description: String,
    val colorOption: String = "Hitam"
)
