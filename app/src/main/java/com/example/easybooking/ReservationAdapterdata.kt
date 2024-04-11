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
        val currentItem = reservations[position]

        holder.restaurantNameTextView.text = currentItem.restaurantName
        holder.partySizeTextView.text = "Party Size: ${currentItem.partySize}"
        holder.timeTextView.text = "Time: ${currentItem.time}"
    }

    override fun getItemCount() = reservations.size

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantNameTextView: TextView = itemView.findViewById(R.id.restaurantNameTextView)
        val partySizeTextView: TextView = itemView.findViewById(R.id.partySizeTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    }
}
