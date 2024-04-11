package com.example.easybooking

data class Reservationdata(
    val restaurantName: String,
    val location: String,
    val date: String,
    val time: String,
    val partySize: Int
)
