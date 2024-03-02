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
            Restaurant("El Cielo", "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá", "Colombian, Molecular Gastronomy Bogota", "320 9189074 prbogota@elcielococinacreativa.com", 4.8, R.drawable.el_cielo),
            Restaurant("Harry Sasson", "Cra. 9 #75-70                               Bogotá", "Colombian, International                     Bogota", "(601) 3477155 prbogota@harrysasson.com", 4.6, R.drawable.harry_sasson),
            Restaurant("Andrés Carne de Res", "Cl. 3 #N° 11A - 56, Chía, Cundinamarca", "Colombian, Steakhouse                        Chia", "317 3311784    Andrescarne@deres.com", 4.5, R.drawable.andres),
            Restaurant("Criterion", "Cl. 81 #11 - 62,                          Bogotá", "French, Colombian                                        Bogotá", "315-748-9826 criterion@restaurant.com", 4.7, R.drawable.criterion),
            Restaurant("Mistura", "Cra. 6 #2-51,                            Cartagena", "Colombian, Seafood                                         Cartagena", "314-567-3920 mistura@restaurant.com", 4.6, R.drawable.mistura),
            Restaurant("Mini-mal", "Cl. 10a #35-11,                          Medellín", "Colombian, Contemporary                                   Medellín", "319-208-6767 minirmal@restaurant.com", 4.5, R.drawable.mini_mal_salon),
            Restaurant("Leo Cocina y Cava", "Carrera 11 # 86 - 49,                 Bogotá", "Colombian, Contemporary                                    Bogotá", "311-908-3355 leo@restaurant.com", 4.8, R.drawable.download),
            Restaurant("Restaurante 1621", "Calle del Torno #39-29,         Cartagena", "French, Caribbean                                           Cartagena", "312-837-9610 1621@restaurant.com", 4.7, R.drawable._621),
            Restaurant("Salvo Patria", "Carrera 37 #8A-43,                   Medellín", "Colombian, Fusion                                                          Medellín", "317-502-1483 salvopatria@restaurant.com", 4.6, R.drawable.salvo),
            Restaurant("La Provincia", "Calle 4b #35-05,                                Cali", "Colombian, Seafood                                                            Cali", "313-711-4927 laprovincia@restaurant.com", 4.4, R.drawable.laprovincia),
            Restaurant("El Santísimo", "Calle del Torno # 39 - 55,                          Cartagena", "Colombian, Fusion                                                   Cartagena", "318-675-8116 elsantisimo@restaurant.com", 4.7, R.drawable.santisimo),
            Restaurant("Restaurante La Vitrola", "Cra. 2 #33-66,                            Cartagena", "International, Colombian                                                 Cartagena", "310-264-7909 lavittrola@restaurant.com", 4.6, R.drawable.vitrola),
            Restaurant("La Cevichería", "Calle Stuart #7-14,                Cartagena", "Seafood, Colombian                                                            Cartagena", "316-689-4257 lacevicheria@restaurant.com", 4.5, R.drawable.cevicher_a),
            Restaurant("Carmen", "Carrera 36 #10A-27,                        Medellín", "Colombian, Fusion                                                                      Medellín", "315-436-9008 carmen@restaurant.com", 4.6, R.drawable.carmen),
            Restaurant("Osaka", "Calle 70 #5 - 23,                             Bogotá", "Japanese, Peruvian                                                                  Bogotá", "320-571-9243 osaka@restaurant.com", 4.7, R.drawable.osaka),
            Restaurant("El Kilo", "Calle 70 # 4 - 47,                          Bogotá", "Colombian, Seafood                                                             Bogotá", "319-802-6633 elkilo@restaurant.com", 4.5, R.drawable.elkilo),
            Restaurant("Restaurante Barcal", "Carrera 37 # 8A - 37,                   Medellín", "Colombian, Fusion                                                    Medellín", "314-908-6733 barcal@restaurant.com", 4.6, R.drawable.barcal),
            Restaurant("Restaurante Versión Original", "Calle 84 #9-08,                         Bogotá", "Colombian, Fusion                                                  Bogotá", "313-220-8554 versionoriginal@restaurant.com", 4.8, R.drawable.download),
            Restaurant("Restaurante Bastión", "Calle de Ayos #3-96,                            Cartagena", "Caribbean, Fusion                                                     Cartagena", "317-693-1482 bastion@restaurant.com", 4.7, R.drawable.bastion)

        )
    }

    override fun onRestaurantItemClick(name: String) {
        // Handle item click here
        Log.d("RestaurantListFragment", "Clicked restaurant: $name")

        // Get the restaurant details
        val restaurant = getSampleRestaurants().find { it.name == name }
        restaurant?.let {
            // Create a bundle with restaurant details
            val bundle = Bundle()
            bundle.putString("RESTAURANT_NAME", it.name)
            bundle.putString("RESTAURANT_LOCATION", it.location)
            bundle.putString("RESTAURANT_PRICE_RANGE", it.priceRange)
            bundle.putInt("RESTAURANT_IMAGE", it.imageResourceId)
            // Navigate to the BookingRestaurantFragment with the bundle
            findNavController().navigate(R.id.action_restaurantListFragment_to_bookingRestaurantFragment, bundle)
        }
    }


    // Function to get the image resource ID based on the restaurant name
    private fun getRestaurantImageResourceId(name: String): Int {
        // Iterate through the list of restaurants and find the matching name
        val restaurant = getSampleRestaurants().find { it.name == name }
        // Return the image resource ID of the found restaurant
        return restaurant?.imageResourceId ?: 0 // Return 0 if restaurant is not found (handle error case)
    }

}
