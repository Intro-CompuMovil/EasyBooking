package com.example.easybooking

import android.Manifest
import android.app.DatePickerDialog
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class BookingRestaurantFragment : Fragment() {

    private val REQUEST_CODE_POST_NOTIFICATIONS = 1
    private var counter = 0 // Initialize the counter variable
    private lateinit var datePickerButton: Button // Declare the datePickerButton globally
    private lateinit var timePickerButton: Button // Declare the timePickerButton globally
    private lateinit var reservarButton: Button // Declare the reservarButton globally
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("reservas")
    }

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
            val user = auth.currentUser
            val userId = user?.uid ?: "Anonymous"
            val userEmail = user?.email ?: "Anonymous"

            val reservationId = database.push().key // Generate a unique key for the reservation

            val reservationData = mapOf(
                "userId" to userId,
                "userEmail" to userEmail,
                "restaurantName" to restaurantName,
                "location" to location,
                "date" to date,
                "time" to time,
                "partySize" to partySize
            )

            reservationId?.let {
                database.child(it).setValue(reservationData)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Reservation saved successfully", Toast.LENGTH_SHORT).show()

                        // Send notification
                        sendNotification("Reservation Confirmed", "Your reservation at $restaurantName is confirmed for $date at $time.")

                        // Navigate to HomeFragment
                        findNavController().navigate(R.id.action_bookingRestaurantFragment_to_myReservationsFragment)
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Failed to save reservation", Toast.LENGTH_SHORT).show()
                    }
            }
        }
        return view
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

            // Save the selected time to Firebase Realtime Database under "reserva" node
            val user = auth.currentUser
            val userId = user?.uid ?: "Anonymous"

            val reservaId = database.child("reserva").push().key
            val reservaData = mapOf(
                "userId" to userId,
                "time" to pickedTime
            )

            reservaId?.let {
                database.child("reserva").child(it).setValue(reservaData)
                    .addOnSuccessListener {
                        Log.d("ReservationInfo", "Time saved successfully")
                    }
                    .addOnFailureListener {
                        Log.e("ReservationInfo", "Failed to save time: $it")
                    }
            }
        }, hour, minute, false) // 12-hour format
        timePickerDialog.show()
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

            // Save the selected date to Firebase Realtime Database under "reserva" node
            val user = auth.currentUser
            val userId = user?.uid ?: "Anonymous"

            val reservaId = database.child("reserva").push().key
            val reservaData = mapOf(
                "userId" to userId,
                "date" to pickedDate
            )

            reservaId?.let {
                database.child("reserva").child(it).setValue(reservaData)
                    .addOnSuccessListener {
                        Log.d("ReservationInfo", "Date saved successfully")
                    }
                    .addOnFailureListener {
                        Log.e("ReservationInfo", "Failed to save date: $it")
                    }
            }
        }, year, month, dayOfMonth)
        datePickerDialog.show()
    }
    private fun sendNotification(title: String, message: String) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                REQUEST_CODE_POST_NOTIFICATIONS
            )
            return
        }

        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel if Android version is Oreo or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("reservation_channel", "Reservation Notifications", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Channel for reservation notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Intent to open HomeFragment when the notification is tapped
        val notificationIntent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("FRAGMENT_TO_LOAD", "MyReservationsFragment")
        }
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(requireContext(), "reservation_channel")
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(requireContext())) {
            notify(0, notification)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_POST_NOTIFICATIONS) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, proceed with sending the notification
                sendNotification("Reservation Confirmed", "Your reservation is confirmed.")
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(requireContext(), "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
