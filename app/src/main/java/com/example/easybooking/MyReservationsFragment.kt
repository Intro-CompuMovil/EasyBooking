package com.example.easybooking
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyReservationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationAdapter: ReservationAdapterdata

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_reservations, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewReservations)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Create a list of reservations (dummy data for now)
        val reservations = createDummyReservations()

        reservationAdapter = ReservationAdapterdata(reservations)
        recyclerView.adapter = reservationAdapter

        return view
    }

    private fun createDummyReservations(): List<Reservationdata> {
        // Here, you can create a list of reservations.
        // For demonstration purposes, we'll create a list with dummy data.
        val reservations = mutableListOf<Reservationdata>()
        for (i in 1..10) {
            reservations.add(
                Reservationdata(
                    "Restaurant $i",
                    "Location $i",
                    "Date $i",
                    "Time $i",
                    i // Dummy party size
                )
            )
        }
        return reservations
    }
}
