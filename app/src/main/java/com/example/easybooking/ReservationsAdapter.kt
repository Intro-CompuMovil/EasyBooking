package com.example.easybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ReservationsAdapter(private val reservations: List<Reservation>) :
    RecyclerView.Adapter<ReservationsAdapter.ReservationViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_reservations, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(reservations[position])

        // Manejar la selección de la reserva
        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
        }

    }

    override fun getItemCount(): Int = reservations.size

    fun getSelectedReservation(): Reservation? {
        return if (selectedPosition != RecyclerView.NO_POSITION) {
            reservations[selectedPosition]
        } else {
            null
        }
    }

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(reservation: Reservation) {

        }
    }
}
