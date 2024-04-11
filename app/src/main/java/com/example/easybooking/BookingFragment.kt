package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BookingFragment : Fragment() {
    private lateinit var TransButton: Button
    private val reservations: MutableList<Reservationdata> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationAdapter: ReservationAdapterdata
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewReservations)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        reservationAdapter = ReservationAdapterdata(reservations)
        recyclerView.adapter = reservationAdapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }
    fun addReservation(reservation: Reservationdata) {
        reservations.add(reservation)
        reservationAdapter.notifyDataSetChanged()
    }

}
