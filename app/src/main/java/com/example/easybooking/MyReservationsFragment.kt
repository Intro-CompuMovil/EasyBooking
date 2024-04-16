package com.example.easybooking
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyReservationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationAdapter: ReservationAdapterdata
    private lateinit var transportButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_reservations, container, false)

        transportButton = view.findViewById(R.id.transportButton)

        // Retrieve reservation information from arguments bundle
        val restaurantName = arguments?.getString("RESTAURANT_NAME")
        val location = arguments?.getString("RESTAURANT_LOCATION")
        val date = arguments?.getString("RESERVATION_DATE")
        val time = arguments?.getString("RESERVATION_TIME")
        val partySize = arguments?.getInt("PARTY_SIZE")

        // Display reservation information on TextView
        val reservationInfoTextView: TextView = view.findViewById(R.id.reservationInfoTextView)
        val reservationInfo = "Reservation Information:\n" +
                "Restaurant: $restaurantName\n" +
                "Location: $location\n" +
                "Date: $date\n" +
                "Time: $time\n" +
                "Party Size: $partySize"
        reservationInfoTextView.text = reservationInfo


        transportButton.setOnClickListener {
            findNavController().navigate(R.id.action_myReservationsFragment_to_rutasFragment)
        }

        return view
    }
}
