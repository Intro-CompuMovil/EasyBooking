package com.example.easybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(private val hotels: List<Hotel>, private val listener: HotelItemClickListener) :
    RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {
    interface HotelItemClickListener {
        fun onHotelItemClick(name: String)
    }

    // ViewHolder class holds references to the views for each item
    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        val starsTextView: TextView = itemView.findViewById(R.id.starsTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val amenitiesTextView: TextView = itemView.findViewById(R.id.amenitiesTextView)
        val HotelImageView: ImageView = itemView.findViewById(R.id.hotelImageView) // Define ImageView



    }

    // Create ViewHolder instances for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(itemView)
    }

    // Bind data to the views in each ViewHolder
    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val currentHotel= hotels[position]
        holder.nameTextView.text = currentHotel.name
        holder.locationTextView.text = currentHotel.location
        holder.starsTextView.text = currentHotel.stars.toString()
        holder.priceTextView.text = currentHotel.pricePerNight.toString()
        holder.amenitiesTextView.text = currentHotel.amenities
        holder.HotelImageView.setImageResource(currentHotel.imageResourceId)

        // Handle item click
        holder.itemView.setOnClickListener {
            listener.onHotelItemClick(currentHotel.name)
        }
    }

    // Return the total number of items in the data set
    override fun getItemCount(): Int {
        return hotels.size
    }
}