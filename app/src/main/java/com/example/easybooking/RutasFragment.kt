package com.example.easybooking

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RutasFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var btnCalculate: Button
    private lateinit var restaurantSpinner: Spinner

    private val startPoint = GeoPoint(4.628698, -74.06365)
    private var endPoint: GeoPoint? = null

    private val restaurants = listOf(
        Restaurant("El cielo", "4.6514381,-74.0545997", "Fine Dining", "$$$$", 4.8, R.drawable.restaurant_icon),
        Restaurant("Harry Sasson", "4.659312421826357,-74.05457768165574", "Contemporary", "$$$$", 4.6, R.drawable.restaurant_icon),
        Restaurant("Andrés Carne de Res", "4.854371584087778,-74.06540323264043", "Steakhouse", "$$$", 4.5, R.drawable.restaurant_icon),
        Restaurant("Criterion", "4.651741762609061,-74.05601951263216", "French", "$$$$", 4.7, R.drawable.restaurant_icon),
        Restaurant("Mistura", "4.7248566849671025,-74.053616786269", "Fusion", "$$$", 4.6, R.drawable.restaurant_icon),
        Restaurant("Mini-mal", "4.642501941984602,-74.06033257617905", "Contemporary", "$$", 4.5, R.drawable.restaurant_icon),
        Restaurant("Leo Cocina y Cava", "4.64804473792112,-74.05643815773897", "Fine Dining", "$$$$", 4.8, R.drawable.restaurant_icon),
        Restaurant("Restaurante 1621", "10.4287790566288,-75.54828916173062", "French", "$$$$", 4.7, R.drawable.restaurant_icon),
        Restaurant("Salvo Patria", "4.6435524053586335,-74.0601029005077", "Contemporary", "$$", 4.6, R.drawable.restaurant_icon),
        Restaurant("La Provincia", "4.796805321339548,-74.11266113726366", "Mediterranean", "$$$", 4.4, R.drawable.restaurant_icon)
        // Agrega los demás restaurantes aquí
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rutas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Configuration.getInstance().load(requireContext(), requireContext().getSharedPreferences("osmdroid", Context.MODE_PRIVATE))

        mapView = view.findViewById(R.id.map)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(startPoint)

        btnCalculate = view.findViewById(R.id.btnCalculateRoute)
        restaurantSpinner = view.findViewById(R.id.restaurant_spinner)

        val restaurantNames = restaurants.map { it.name }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, restaurantNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        restaurantSpinner.adapter = adapter

        restaurantSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedRestaurant = restaurants[position]
                val latLng = selectedRestaurant.location.split(",")
                endPoint = GeoPoint(latLng[0].toDouble(), latLng[1].toDouble())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No action needed
            }
        }

        btnCalculate.setOnClickListener {
            if (endPoint != null) {
                fetchRoute(startPoint, endPoint!!)
            } else {
                Toast.makeText(requireContext(), "Selecciona un restaurante", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchRoute(start: GeoPoint, destination: GeoPoint) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RouteService::class.java)

        val startString = "${start.longitude},${start.latitude}"
        val endString = "${destination.longitude},${destination.latitude}"

        service.getRoute("YOUR_API_KEY", startString, endString).enqueue(object : retrofit2.Callback<RouteResponse> {
            override fun onResponse(call: retrofit2.Call<RouteResponse>, response: retrofit2.Response<RouteResponse>) {
                if (response.isSuccessful) {
                    val routeResponse = response.body()
                    if (routeResponse != null) {
                        drawRoute(routeResponse)
                    } else {
                        Log.i("Route", "Response body is null")
                    }
                } else {
                    Log.i("Route", "Error: ${response.code()} - ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<RouteResponse>, t: Throwable) {
                Log.e("Route", "Failure: ${t.message}")
            }
        })
    }

    private fun drawRoute(routeResponse: RouteResponse) {
        val polyline = Polyline()
        val geoPoints = routeResponse.features.first().geometry.coordinates.map {
            GeoPoint(it[1], it[0])
        }
        polyline.setPoints(geoPoints)
        polyline.outlinePaint.color = Color.BLUE
        polyline.outlinePaint.strokeWidth = 10f

        mapView.overlays.clear()
        mapView.overlays.add(polyline)
        mapView.invalidate()
    }

    interface RouteService {
        @GET("v2/directions/driving-car")
        fun getRoute(
            @Query("api_key") apiKey: String,
            @Query("start") start: String,
            @Query("end") end: String
        ): retrofit2.Call<RouteResponse>
    }

    data class RouteResponse(
        val features: List<Feature>
    )

    data class Feature(
        val geometry: Geometry
    )

    data class Geometry(
        val coordinates: List<List<Double>>
    )
}
