package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TransportReservationFragment : Fragment() {

    companion object {
        private const val ARG_SELECTED_RESERVATION = "selected_reservation"

        fun newInstance(selectedReservation: Reservation): TransportReservationFragment {
            val fragment = TransportReservationFragment()
            val args = Bundle()
            args.putParcelable(ARG_SELECTED_RESERVATION, selectedReservation)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var selectedReservation: Reservation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transport_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedReservation = requireArguments().getParcelable(ARG_SELECTED_RESERVATION)!!

    }
}

