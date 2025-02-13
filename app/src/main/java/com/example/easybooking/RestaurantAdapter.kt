package com.example.easybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(private val restaurants: List<Restaurant>, private val listener: RestaurantListFragment) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    interface RestaurantItemClickListener {
        fun onRestaurantItemClick(name: String)
    }

    // ViewHolder class holds references to the views for each item
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        //val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        val cuisineTextView: TextView = itemView.findViewById(R.id.cuisineTextView)
        //val priceRangeTextView: TextView = itemView.findViewById(R.id.priceRangeTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val restaurantImageView: ImageView = itemView.findViewById(R.id.restaurantImageView) // Define ImageView



    }

    // Create ViewHolder instances for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(itemView)
    }

    // Bind data to the views in each ViewHolder
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = restaurants[position]
        holder.nameTextView.text = currentRestaurant.name
        //holder.locationTextView.text = currentRestaurant.location
        holder.cuisineTextView.text = currentRestaurant.cuisine
        //holder.priceRangeTextView.text = currentRestaurant.priceRange
        holder.ratingTextView.text = currentRestaurant.rating.toString()
        holder.restaurantImageView.setImageResource(currentRestaurant.imageResourceId)

        // Handle item click
        holder.itemView.setOnClickListener {
            listener.onRestaurantItemClick(currentRestaurant.name)
        }
    }

    // Return the total number of items in the data set
    override fun getItemCount(): Int {
        return restaurants.size
    }
}