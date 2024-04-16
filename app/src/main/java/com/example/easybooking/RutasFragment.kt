package com.example.easybooking

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RutasFragment : Fragment(), OnMapReadyCallback {

        private lateinit var map: GoogleMap
        private lateinit var btnCalculate : Button

        private var start : String = ""
        private var end : String = ""

        var poly: Polyline? = null

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_rutas, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            btnCalculate = view.findViewById(R.id.btnCalculateRoute)
            btnCalculate.setOnClickListener{
                start = ""
                end = ""
                poly?.remove()
                poly = null
                if(::map.isInitialized){
                    map.setOnMapClickListener {
                        if(start.isEmpty()){
                            start = "${it.longitude},${it.latitude}"
                        }else if(end.isEmpty()){
                            end = "${it.longitude},${it.latitude}"
                        }else{
                            createRoute()
                        }
                    }
                }
            }

            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
        }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
    }

    private fun createRoute(){
        Log.i("aris", start)
        Log.i("aris", end)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetroFit().create(ApiService::class.java).getRoute("5b3ce3597851110001cf6248c3e5e7119fe74574b2cc15050002e1e5", start, end)
                if(call.isSuccessful){
                    drawRoute(call.body())
                    val response = call.body()
                    if (response != null) {
                        // Aquí procesa la respuesta como desees
                        Log.i("aris", "Response: $response")
                    } else {
                        Log.i("aris", "Response body is null")
                    }
                } else {
                    val errorBody = call.errorBody()?.string()
                    Log.i("aris", "Error: ${call.code()} - $errorBody")
                }
            } catch (e: Exception) {
                Log.e("aris", "Exception: ${e.message}")
            }
        }
    }

    private fun drawRoute(routeResponse: RouteResponse?) {
        val polylineOptions = PolylineOptions()
        routeResponse?.features?.first()?.geometry?.coordinates?.forEach {
            polylineOptions.add(LatLng(it[1], it[0]))
        }
        view?.post {
            poly = map.addPolyline(polylineOptions)
        }
    }


    private fun getRetroFit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}