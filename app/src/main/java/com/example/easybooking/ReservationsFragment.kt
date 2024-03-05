package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.fragment.app.Fragment

class ReservationsFragment : Fragment() {

    private lateinit var reservationsAdapter: ReservationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reservationsAdapter = ReservationsAdapter(getSampleReservations())
        recyclerViewReservations.layoutManager = ConstraintLayoutManager(requireContext())
        recyclerViewReservations.adapter = reservationsAdapter

        btnReserveTransport.setOnClickListener {
            val selectedReservation = reservationsAdapter.getSelectedReservation()
            if (selectedReservation != null) {
                // Abre el fragmento de reserva de transporte
                val transportReservationFragment = TransportReservationFragment.newInstance(selectedReservation)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, transportReservationFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun getSampleReservations(): List<Reservation> {
        // Implementa la lógica para obtener las reservas desde tu fuente de datos
        return listOf(
            Reservation("Reserva 1", "Descripción de la reserva 1"),
            Reservation("Reserva 2", "Descripción de la reserva 2"),
            Reservation("Reserva 3", "Descripción de la reserva 3")
        )
    }
}
