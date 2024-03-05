package com.example.easybooking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelListFragmentTop : Fragment(), HotelAdapter.HotelItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HotelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = HotelAdapter(getTopRatedHotels(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun getTopRatedHotels(): List<Hotel> {
        // Ordena la lista de hoteles por su calificación en orden descendente y toma los primeros 10
        return getSampleHotels().sortedByDescending { it.stars }.take(10)
    }

    private fun getSampleHotels(): List<Hotel> {
        // Aquí defines la lista de hoteles de muestra
        return listOf(

            Hotel("Hotel Termales del Otoño", "Manizales", 5, 200,
                "Wifi Minibar, Spa, Hot Springs, Gym, Parking", R.drawable.termales_del_oto_o),

            Hotel("Hotel Bogotá Palace", "Bogotá",4,120,
                "WiFi Parking Restaurant", R.drawable.bogpalace),

            Hotel("Cartagena Beach Resort", "Cartagena", 5, 300,
                "WiFi Beach Access Spa Pool", R.drawable.cartagena_beach_resort),

            Hotel("Medellín Downtown Suites", "Medellín", 3, 80,
                "WiFi Breakfast Included", R.drawable.medell_n_downtown_suites),

            Hotel("Santa Marta Ocean view Hotel", "Santa Marta", 4, 180,
                "WiFi Pool Gym", R.drawable.santa_marta_oceanview_hotel),

            Hotel("Cali Riverside Inn", "Cali", 3, 70,
                "WiFi Parking", R.drawable.cali_riverside_inn),

            Hotel("San Andrés Island Resort", "San Andrés", 5, 250,
                "WiFi Beach Access Spa Pool", R.drawable.san_andr_s_island_resort),

            Hotel("Pereira Mountain Lodge", "Pereira", 4, 150,
                "WiFi Scenic Views", R.drawable.pereira_mountain_lodge),

            Hotel("Barranquilla Grand Plaza", "Barranquilla", 4, 130,
                "WiFi Parking Restaurant", R.drawable.barranquilla_grand_plaza),

            Hotel("Villa de Leyva Boutique Hotel", "Villa de Leyva", 4, 160,
                "WiFi Breakfast Included", R.drawable.villa_de_leyva_boutique_hotel),

            Hotel("Popayán Colonial Retreat", "Popayán", 3, 90,
                "WiFi Parking", R.drawable.popay_n_colonial_retreat),

            Hotel("QUO Quality Hotel", "Manizales", 4, 85,
                "WiFi Scenic Views", R.drawable.quo_quality_hotel),

            Hotel("Pasto Lakeside Resort", "Pasto", 3, 95,
                "WiFi Lake Access Breakfast Included", R.drawable.pastoresort),

            Hotel("Cúcuta City Center Hotel", "Cúcuta", 3, 75,
                "WiFi Parking", R.drawable.c_cuta_city_center_hotel),

            Hotel("Armenia Vineyard Retreat", "Armenia", 4, 140,
                "WiFi Vineyard Tours Scenic Views", R.drawable.armenia_vineyard_retreat),

            Hotel("Neiva Desert Oasis", "Neiva", 3, 100,
                "WiFi Pool", R.drawable.neiva_desert_oasis),

            Hotel("Montería Riverside Hotel", "Montería", 3, 80,
                "WiFi River Access Breakfast Included", R.drawable.monter_a_riverside_hotel),

            Hotel("Sincelejo Urban Retreat", "Sincelejo", 3, 70,
                "WiFi Parking", R.drawable.sincelejo_urban_retreat),

            Hotel("Tunja Mountain View Lodge", "Tunja", 3, 90,
                "WiFi Mountain Views Breakfast Included", R.drawable.tunja_mountain_view_lodge),

            Hotel("Riohacha Coastal Resort", "Riohacha", 3, 110,
                "WiFi Beach Access Pool", R.drawable.riohacha_coastal_resort),

            Hotel("Valledupar Cultural Hotel", "Valledupar", 3, 75,
                "WiFi Cultural Tours", R.drawable.valledupar_cultural_hotel),

            Hotel("Suite Jones Hotel", "Bogotá", 4, 85,
                "WiFi City Views Breakfast Included", R.drawable.suite_jones_hotel)


        )
    }

    override fun onHotelItemClick(name: String) {
        // Maneja el clic del elemento aquí
        Log.d("HotelListFragment", "Hotel clickeado: $name")

        // Obtén los detalles del hotel
        val hotel = getTopRatedHotels().find { it.name == name }
        hotel?.let {
            // Crea un Bundle con los detalles del hotel
            val bundle = Bundle().apply {
                putString("HOTEL_NAME", it.name)
                putString("HOTEL_LOCATION", it.location)
                putString("HOTEL_AMENITIES", it.amenities)
                putInt("HOTEL_IMAGE", it.imageResourceId)
            }

            // Navega al Fragment de BookingHotelFragment con el Bundle
            findNavController().navigate(R.id.action_hotelListFragment_to_BookingHotelFragment, bundle)
        }
    }
}

