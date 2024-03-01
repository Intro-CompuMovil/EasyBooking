package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RestaurantListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = RestaurantAdapter(getSampleRestaurants())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun getSampleRestaurants(): List<Restaurant> {
        // Create sample restaurants
        return listOf(
            Restaurant("El Cielo", "Bogotá", "Colombian, Molecular Gastronomy", "$$$", 4.8),
            Restaurant("Harry Sasson", "Bogotá", "Colombian, International", "$$$", 4.6),
            Restaurant("Andrés Carne de Res", "Chía", "Colombian, Steakhouse", "$$", 4.5),
            Restaurant("Criterion", "Bogotá", "French, Colombian", "$$$", 4.7),
            Restaurant("Mistura", "Cartagena", "Colombian, Seafood", "$$$", 4.6),
            Restaurant("Mini-mal", "Medellín", "Colombian, Contemporary", "$$", 4.5),
            Restaurant("Leo Cocina y Cava", "Bogotá", "Colombian, Contemporary", "$$$", 4.8),
            Restaurant("Criterión", "Barranquilla", "International, Colombian", "$$$", 4.5),
            Restaurant("Restaurante 1621", "Cartagena", "French, Caribbean", "$$$", 4.7),
            Restaurant("Salvo Patria", "Medellín", "Colombian, Fusion", "$$", 4.6),
            Restaurant("La Provincia", "Cali", "Colombian, Seafood", "$$", 4.4),
            Restaurant("El Santísimo", "Cartagena", "Colombian, Fusion", "$$", 4.7),
            Restaurant("Restaurante La Vitrola", "Cartagena", "International, Colombian", "$$$", 4.6),
            Restaurant("La Cevichería", "Cartagena", "Seafood, Colombian", "$$", 4.5),
            Restaurant("Carmen", "Medellín", "Colombian, Fusion", "$$", 4.6),
            Restaurant("Osaka", "Bogotá", "Japanese, Peruvian", "$$$", 4.7),
            Restaurant("El Kilo", "Bogotá", "Colombian, Seafood", "$$", 4.5),
            Restaurant("Restaurante Barcal", "Medellín", "Colombian, Fusion", "$$", 4.6),
            Restaurant("Restaurante Versión Original", "Bogotá", "Colombian, Fusion", "$$$", 4.8),
            Restaurant("Restaurante Bastión", "Cartagena", "Caribbean, Fusion", "$$$", 4.7)

        )
    }
}
