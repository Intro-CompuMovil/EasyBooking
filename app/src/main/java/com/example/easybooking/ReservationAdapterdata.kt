package com.example.easybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapterdata(private val reservations: List<Reservationdata>) :
    RecyclerView.Adapter<ReservationAdapterdata.ReservationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reservationdata, parent, false)
        return ReservationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val currentReservation = reservations[position]
        holder.bind(currentReservation)
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewReservationName: TextView = itemView.findViewById(R.id.textViewReservationName)
        private val textViewLocation: TextView = itemView.findViewById(R.id.textViewLocation)
        private val textViewDateTime: TextView = itemView.findViewById(R.id.textViewDateTime)
        private val textViewPartySize: TextView = itemView.findViewById(R.id.textViewPartySize)

        fun bind(reservation: Reservationdata) {
            textViewReservationName.text = reservation.restaurantName
            textViewLocation.text = "Location: ${reservation.location}"
            textViewDateTime.text = "Date/Time: ${reservation.date}, ${reservation.time}"
            textViewPartySize.text = "Party Size: ${reservation.partySize}"
        }
    }
}


