package com.example.easybooking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelListFragment : Fragment(), HotelAdapter.HotelItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HotelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = HotelAdapter(getSampleHotels(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun getSampleHotels(): List<Hotel> {
        // Create sample hotels
        return listOf(

            Hotel("Hotel Termales del Otoño", "Manizales", 5, 200,
                "Wifi Minibar, Spa, Hot Springs, Gym, Parking", R.drawable.termales_del_oto_o),

            Hotel("Hotel Bogotá Palace", "Bogotá",4,120,
                "WiFi Parking Restaurant", R.drawable.hotel_bogot__palace),

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
                "WiFi Lake Access Breakfast Included", R.drawable.pasto_lakeside_resort),

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
        // Handle item click here
        Log.d("HotelListFragment", "Clicked Hotel: $name")

        // Get the Hotel details
        val hotel = getSampleHotels().find { it.name == name }
        hotel?.let {
            // Create a bundle with Hotel details
            val bundle = Bundle()
            bundle.putString("HOTEL_NAME", it.name)
            bundle.putString("HOTEL_LOCATION", it.location)
            bundle.putString("HOTEL_PRICE_RANGE", it.amenities)
            bundle.putInt("HOTEL_IMAGE", it.imageResourceId)
            // Navigate to the BookingHotelFragment with the bundle
            // findNavController().navigate(R.id.action_HotelListFragment_to_bookingHotelFragment, bundle)
        }
    }


    // Function to get the image resource ID based on the Hotel name

    private fun getHotelImageResourceId(name: String): Int {
            // Iterate through the list of Hotel and find the matching name
        val Hotel = getSampleHotels().find { it.name == name }
            // Return the image resource ID of the found Hotel
       return Hotel?.imageResourceId ?: 0 // Return 0 if Hotel is not found (handle error case)
    }

}
