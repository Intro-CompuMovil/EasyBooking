package com.example.easybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapterdata(private val reservations: List<Reservationdata>) : RecyclerView.Adapter<ReservationAdapterdata.ReservationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reservationdata, parent, false)
        return ReservationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val currentReservation = reservations[position]
        holder.bind(currentReservation)
    }

    override fun getItemCount() = reservations.size

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val restaurantNameTextView: TextView = itemView.findViewById(R.id.restaurantNameTextView)
        private val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        private val dateTimeTextView: TextView = itemView.findViewById(R.id.dateTimeTextView)
        private val partySizeTextView: TextView = itemView.findViewById(R.id.partySizeTextView)

        fun bind(reservation: Reservationdata) {
            restaurantNameTextView.text = reservation.restaurantName
            locationTextView.text = reservation.location
            dateTimeTextView.text = "${reservation.date}, ${reservation.time}"
            partySizeTextView.text = "Party size: ${reservation.partySize}"
        }
    }
}

