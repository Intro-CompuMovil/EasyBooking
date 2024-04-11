package com.example.easybooking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import java.util.Calendar
import java.util.Locale


class BookedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booked, container, false)

        // Retrieve the restaurant details from arguments
        val restaurantName = arguments?.getString("RESTAURANT_NAME")
        val location = arguments?.getString("RESTAURANT_LOCATION")

        // Display the restaurant name
        val restaurantNameTextView: TextView = view.findViewById(R.id.restaurantNameTextView)
        restaurantNameTextView.text = restaurantName

        // Display the restaurant location
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        locationTextView.text = "Location: $location"

        return view
    }
}
