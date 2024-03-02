package com.example.easybooking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RestaurantListFragment : Fragment(), RestaurantAdapter.RestaurantItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = RestaurantAdapter(getSampleRestaurants(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun getSampleRestaurants(): List<Restaurant> {
        // Create sample restaurants
        return listOf(
            Restaurant("El Cielo", "Bogotá", "Colombian, Molecular Gastronomy", "$$$", 4.8, R.drawable.el_cielo),
            Restaurant("Harry Sasson", "Bogotá", "Colombian, International", "$$$", 4.6, R.drawable.harry_sasson),
            Restaurant("Andrés Carne de Res", "Chía", "Colombian, Steakhouse", "$$", 4.5, R.drawable.andres),
            Restaurant("Criterion", "Bogotá", "French, Colombian", "$$$", 4.7, R.drawable.criterion),
            Restaurant("Mistura", "Cartagena", "Colombian, Seafood", "$$$", 4.6, R.drawable.mistura),
            Restaurant("Mini-mal", "Medellín", "Colombian, Contemporary", "$$", 4.5, R.drawable.mini_mal_salon),
            Restaurant("Leo Cocina y Cava", "Bogotá", "Colombian, Contemporary", "$$$", 4.8, R.drawable.download),
            Restaurant("Restaurante 1621", "Cartagena", "French, Caribbean", "$$$", 4.7, R.drawable._621),
            Restaurant("Salvo Patria", "Medellín", "Colombian, Fusion", "$$", 4.6, R.drawable.salvo),
            Restaurant("La Provincia", "Cali", "Colombian, Seafood", "$$", 4.4, R.drawable.laprovincia),
            Restaurant("El Santísimo", "Cartagena", "Colombian, Fusion", "$$", 4.7, R.drawable.santisimo),
            Restaurant("Restaurante La Vitrola", "Cartagena", "International, Colombian", "$$$", 4.6, R.drawable.vitrola),
            Restaurant("La Cevichería", "Cartagena", "Seafood, Colombian", "$$", 4.5, R.drawable.cevicher_a),
            Restaurant("Carmen", "Medellín", "Colombian, Fusion", "$$", 4.6, R.drawable.carmen),
            Restaurant("Osaka", "Bogotá", "Japanese, Peruvian", "$$$", 4.7, R.drawable.osaka),
            Restaurant("El Kilo", "Bogotá", "Colombian, Seafood", "$$", 4.5, R.drawable.elkilo),
            Restaurant("Restaurante Barcal", "Medellín", "Colombian, Fusion", "$$", 4.6, R.drawable.barcal),
            Restaurant("Restaurante Versión Original", "Bogotá", "Colombian, Fusion", "$$$", 4.8, R.drawable.download),
            Restaurant("Restaurante Bastión", "Cartagena", "Caribbean, Fusion", "$$$", 4.7, R.drawable.bastion)
        )
    }

    override fun onRestaurantItemClick(name: String) {
        // Handle item click here
        Log.d("RestaurantListFragment", "Clicked restaurant: $name")

        // Navigate to the BookingRestaurantFragment with the restaurant name as an argument
        val bundle = Bundle()
        bundle.putString("RESTAURANT_NAME", name)
        findNavController().navigate(R.id.action_restaurantListFragment_to_bookingRestaurantFragment, bundle)
    }
}
