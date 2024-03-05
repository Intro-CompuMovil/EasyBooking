package com.example.easybooking

data class TopRestaurant(
    //mi adapter de restaurantes
    val name: String,
    val location: String,
    val cuisine: String,
    val priceRange: String,
    val rating: Double,
    val imageResourceId: Int
)
