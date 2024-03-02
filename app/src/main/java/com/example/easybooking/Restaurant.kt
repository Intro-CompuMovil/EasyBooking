package com.example.easybooking

data class Restaurant(
    val name: String,
    val location: String,
    val cuisine: String,
    val priceRange: String,
    val rating: Double,
    val imageResourceId: Int
)