package com.example.easybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapter(private val reservations: List<Reservationdata>) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    inner class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantNameTextView: TextView = itemView.findViewById(R.id.restaurantNameTextView)
        val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val partySizeTextView: TextView = itemView.findViewById(R.id.partySizeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reservationdata, parent, false)
        return ReservationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val currentReservation = reservations[position]
        holder.restaurantNameTextView.text = currentReservation.restaurantName
        holder.locationTextView.text = currentReservation.location
        holder.timeTextView.text = currentReservation.time
        holder.partySizeTextView.text = currentReservation.partySize.toString()
    }

    override fun getItemCount(): Int {
        return reservations.size
    }
}
