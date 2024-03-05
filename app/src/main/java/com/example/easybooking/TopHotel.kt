package com.example.easybooking

data class TopHotel(
    val name: String,
    val location: String,
    val stars: Int,
    val pricePerNight: Int,
    val amenities: String,
    val imageResourceId: Int
)