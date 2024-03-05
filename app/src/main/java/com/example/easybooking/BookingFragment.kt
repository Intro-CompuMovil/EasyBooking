package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservations, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewReservations)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = BookingAdapter(getSampleData())

        return view
    }

    private fun getSampleData(): List<Reservation> {

        return emptyList()
    }
}
