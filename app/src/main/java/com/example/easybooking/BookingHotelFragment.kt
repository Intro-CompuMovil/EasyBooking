package com.example.easybooking

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class BookingHotelFragment : Fragment() {

    private var counter = 0 // Initialize the counter variable
    private lateinit var datePickerButton: Button // Declare the datePickerButton globally
    private lateinit var timePickerButton: Button // Declare the timePickerButton globally
    private lateinit var reservarButton: Button // Declare the reservarButton globally
    private var selectedDates: MutableList<Long> = mutableListOf() // Store selected dates

    companion object {
        private const val CHANNEL_ID = "booking_notifications"
        private const val NOTIFICATION_ID = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_hotel, container, false)

        // Create notification channel
        createNotificationChannel()

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
            sendNotification(hotelName, location)
            findNavController().navigate(R.id.action_bookingHotelFragment_to_pagoFragment22)
        }

        return view
    }

    private fun showDatePickerDialog() {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val picker = builder.build()
        picker.addOnPositiveButtonClickListener { selection ->
            // Store selected dates
            selectedDates.clear()
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection.first ?: return@addOnPositiveButtonClickListener
            val startDate = calendar.time
            calendar.timeInMillis = selection.second ?: return@addOnPositiveButtonClickListener
            val endDate = calendar.time

            // Format the selected dates
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedStartDate = dateFormat.format(startDate)
            val formattedEndDate = dateFormat.format(endDate)

            // Update the button text
            datePickerButton.text = "$formattedStartDate - $formattedEndDate"

            // Store selected dates as timestamps
            val daysBetween = TimeUnit.MILLISECONDS.toDays(endDate.time - startDate.time)
            for (i in 0..daysBetween) {
                val date = Calendar.getInstance()
                date.time = startDate
                date.add(Calendar.DATE, i.toInt())
                selectedDates.add(date.timeInMillis)
            }
        }
        picker.show(parentFragmentManager, picker.toString())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(hotelName: String?, location: String?) {
        val notificationBuilder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle("Reserva Confirmada")
            .setContentText("Tu reserva en $hotelName en $location ha sido confirmada.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Handle permission request
                return
            }
            notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }

}
