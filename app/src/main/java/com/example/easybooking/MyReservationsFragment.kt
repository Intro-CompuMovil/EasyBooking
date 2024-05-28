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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyReservationsFragment : Fragment() {

    private lateinit var transportButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationAdapter: ReservationAdapter
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_reservations, container, false)

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("reservations")

        // Retrieve the current user's ID
        val userId = auth.currentUser?.uid ?: ""

        // Initialize RecyclerView and ReservationAdapter
        recyclerView = view.findViewById(R.id.recyclerViewReservations)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        reservationAdapter = ReservationAdapter(emptyList())
        recyclerView.adapter = reservationAdapter

        // Retrieve reservation information from Firebase
        database.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reservations = mutableListOf<Reservationdata>()
                for (reservationSnapshot in snapshot.children) {
                    val reservation = reservationSnapshot.getValue(Reservationdata::class.java)
                    reservation?.let {
                        reservations.add(it)
                    }
                }
                reservationAdapter.setData(reservations)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Set up transportButton
        transportButton = view.findViewById(R.id.transportButton)
        transportButton.setOnClickListener {
            findNavController().navigate(R.id.action_myReservationsFragment_to_rutasFragment)
        }

        return view
    }
}
