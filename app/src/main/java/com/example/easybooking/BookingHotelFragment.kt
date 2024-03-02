package com.example.easybooking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class BookingHotelFragment : Fragment() {

    private var counter = 0 // Initialize the counter variable
    private lateinit var datePickerButton: Button // Declare the datePickerButton globally
    private lateinit var timePickerButton: Button // Declare the timePickerButton globally
    private lateinit var reservarButton: Button // Declare the reservarButton globally

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_hotel, container, false)

        // Retrieve the hotel details from arguments
        val hotelName = arguments?.getString("HOTEL_NAME")
        val location = arguments?.getString("HOTEL_LOCATION")
        val amenities = arguments?.getString("HOTEL_AMENITIES")
        val imageResourceId = arguments?.getInt("HOTEL_IMAGE")

        // Display the hotel name
        val hotelNameTextView: TextView = view.findViewById(R.id.hotelNameTextView)
        hotelNameTextView.text = hotelName

        // Display the hotel location
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        locationTextView.text = "Location: $location"

        // Display the hotel amenities
        val amenitiesTextView: TextView = view.findViewById(R.id.amenitiesTextView)
        amenitiesTextView.text = "Amenities: $amenities"

        // Display the hotel photo
        val hotelImageView: ImageView = view.findViewById(R.id.hotelImageView)
        imageResourceId?.let { hotelImageView.setImageResource(it) }

        // Set up button to open DatePicker dialog
        datePickerButton = view.findViewById(R.id.datePickerButton)
        datePickerButton.setOnClickListener {
            showDatePickerDialog()
        }

        // Set up button to open TimePicker dialog
        timePickerButton = view.findViewById(R.id.timePickerButton)
        timePickerButton.setOnClickListener {
            showTimePickerDialog()
        }

        // Set up counter functionality
        val counterTextView: TextView = view.findViewById(R.id.counterTextView)
        val minusButton: Button = view.findViewById(R.id.minusButton)
        val plusButton: Button = view.findViewById(R.id.plusButton)

        minusButton.setOnClickListener {
            if (counter > 0) { // Check if counter is greater than 0 before decrementing
                counter--
                counterTextView.text = counter.toString()
            }
        }

        plusButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }

        // Set up "Reservar" button
        reservarButton = view.findViewById(R.id.reservarButton)
        reservarButton.setOnClickListener {
            reservarButton.isEnabled = false // Disable the button
            Toast.makeText(requireContext(), "Su reserva se ha hecho satisfactoriamente", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            // Handle the selected date
            // You can access the selected year, month, and dayOfMonth here
            val pickedDate = "$dayOfMonth/${month + 1}/$year"
            datePickerButton.text = pickedDate
        }, year, month, dayOfMonth)
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            // Handle the selected time
            // You can access the selected hourOfDay and minute here
            val format = if (hourOfDay < 12) "AM" else "PM"
            val hour12 = if (hourOfDay > 12) hourOfDay - 12 else hourOfDay
            val pickedTime = String.format(Locale.getDefault(), "%02d:%02d %s", hour12, minute, format)
            timePickerButton.text = pickedTime
        }, hour, minute, false) // 12-hour format
        timePickerDialog.show()
    }
}
