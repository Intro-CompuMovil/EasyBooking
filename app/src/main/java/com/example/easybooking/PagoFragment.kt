package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PagoFragment : Fragment() {

    private lateinit var btnPay: Button
    private lateinit var tvHotelName: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvAmenities: TextView
    private lateinit var tvDates: TextView
    private lateinit var tvGuests: TextView
    private lateinit var ivHotelImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pago, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPay = view.findViewById(R.id.botonPagar)
        tvHotelName = view.findViewById(R.id.tvHotelName)
        tvLocation = view.findViewById(R.id.tvLocation)
        tvAmenities = view.findViewById(R.id.tvAmenities)
        tvDates = view.findViewById(R.id.tvDates)
        tvGuests = view.findViewById(R.id.tvGuests)
        ivHotelImage = view.findViewById(R.id.imagenEstablecimientoImageView)

        btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_comentariosFragment_to_paymentFragment)
        }

        // Retrieve and display the booking information from Firestore
        fetchBookingData()
    }

    private fun fetchBookingData() {
        val db = FirebaseFirestore.getInstance()
        val bookingRef = db.collection("pago")

        // Fetch the most recent booking (assuming the most recent one is the last added)
        bookingRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    // No booking found
                    return@addOnSuccessListener
                }

                val booking = documents.first()
                val hotelName = booking.getString("hotelName")
                val location = booking.getString("location")
                val amenities = booking.getString("amenities")
                val selectedDates = booking.get("selectedDates") as List<Long>
                val numberOfGuests = booking.getLong("numberOfGuests")?.toInt()
                val imageResourceId = booking.getString("hotelImage")

                // Format the selected dates
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDates = selectedDates.map { dateFormat.format(Date(it)) }

                // Display the booking information
                tvHotelName.text = "Hotel: $hotelName"
                tvLocation.text = "Location: $location"
                tvAmenities.text = "Amenities: $amenities"
                tvDates.text = "Dates: ${formattedDates.joinToString(" - ")}"
                tvGuests.text = "Number of Guests: $numberOfGuests"

                // Display the hotel image if available
                if (imageResourceId != null) {
                    val resId = resources.getIdentifier(imageResourceId, "drawable", requireContext().packageName)
                    ivHotelImage.setImageResource(resId)
                }
            }
            .addOnFailureListener { e ->
                // Handle the error
            }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagoFragment().apply {
                arguments = Bundle().apply {
                    // Initialize any arguments if necessary
                }
            }
    }
}
