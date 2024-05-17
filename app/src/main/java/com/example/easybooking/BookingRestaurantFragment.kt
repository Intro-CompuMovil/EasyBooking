package com.example.easybooking

import android.Manifest
import android.app.DatePickerDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import java.util.*

class BookingRestaurantFragment : Fragment() {

    private var counter = 0 // Initialize the counter variable
    private lateinit var datePickerButton: Button // Declare the datePickerButton globally
    private lateinit var timePickerButton: Button // Declare the timePickerButton globally
    private lateinit var reservarButton: Button // Declare the reservarButton globally

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_restaurant, container, false)

        // Retrieve the restaurant details from arguments
        val restaurantName = arguments?.getString("RESTAURANT_NAME")
        val location = arguments?.getString("RESTAURANT_LOCATION")
        val priceRange = arguments?.getString("RESTAURANT_PRICE_RANGE")
        val imageResourceId = arguments?.getInt("RESTAURANT_IMAGE")

        // Display the restaurant name
        val restaurantNameTextView: TextView = view.findViewById(R.id.restaurantNameTextView)
        restaurantNameTextView.text = restaurantName

        // Display the restaurant location
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        locationTextView.text = "Location: $location"

        // Display the restaurant price range
        val priceRangeTextView: TextView = view.findViewById(R.id.priceRangeTextView)
        priceRangeTextView.text = "Contact information: $priceRange"

        // Display the restaurant photo
        val restaurantImageView: ImageView = view.findViewById(R.id.restaurantImageView)
        imageResourceId?.let { restaurantImageView.setImageResource(it) }

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
            val restaurantName = restaurantNameTextView.text.toString()
            val location = locationTextView.text.toString()
            val date = datePickerButton.text.toString()
            val time = timePickerButton.text.toString()
            val partySize = counter
            val reservation = Reservationdata(restaurantName, location, date, time, partySize)
            val args = Bundle().apply {
                putString("RESTAURANT_NAME", restaurantName)
                putString("RESTAURANT_LOCATION", location)
                putString("RESERVATION_DATE", date)
                putString("RESERVATION_TIME", time)
                putInt("PARTY_SIZE", partySize)
            }
            (activity as? BookingFragment)?.addReservation(reservation)
            reservarButton.isEnabled = false // Disable the button

            // Display a toast to check if information is gathered correctly
            val toastMessage = "Reservation Information:\n" +
                    "Restaurant: $restaurantName\n" +
                    "Location: $location\n" +
                    "Date: $date\n" +
                    "Time: $time\n" +
                    "Party Size: $partySize"
            Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_LONG).show()

            // Log the gathered information
            val logMessage = "Reservation Information: " +
                    "Restaurant: $restaurantName, " +
                    "Location: $location, " +
                    "Date: $date, " +
                    "Time: $time, " +
                    "Party Size: $partySize"
            Log.d("ReservationInfo", logMessage)

            // Send notification
            sendNotification("Reservation Confirmed", "Your reservation at $restaurantName is confirmed for $date at $time.")

            findNavController().navigate(R.id.action_bookingRestaurantFragment_to_myReservationsFragment, args)

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

    private fun sendNotification(title: String, message: String) {
        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel if Android version is Oreo or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("reservation_channel", "Reservation Notifications", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Channel for reservation notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(requireContext(), "reservation_channel")
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(requireContext())) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                        int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(0, notification)
        }
    }

}
