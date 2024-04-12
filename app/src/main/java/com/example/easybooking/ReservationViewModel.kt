package com.example.easybooking

import androidx.lifecycle.ViewModel

class ReservationViewModel : ViewModel() {
    var restaurantName: String? = null
    var location: String? = null
    var date: String? = null
    var time: String? = null
    var partySize: Int? = null
}