package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView

class BookingRestaurantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_restaurant, container, false)

        // Retrieve the restaurant name from arguments
        val restaurantName = arguments?.getString("RESTAURANT_NAME")

        // Display the restaurant name
        val restaurantNameTextView: TextView = view.findViewById(R.id.restaurantNameTextView)
        restaurantNameTextView.text = restaurantName

        return view
    }
}
