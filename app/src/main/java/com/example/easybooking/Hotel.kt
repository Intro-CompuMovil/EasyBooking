package com.example.easybooking

data class Hotel(
    val name: String,
    val location: String,
    val stars: Int,
    val pricePerNight: Int,
    val amenities: String,
    val imageResourceId: Int
)
