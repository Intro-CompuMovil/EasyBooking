package com.example.easybooking

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near the specified address.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val location = LatLng(4.6514381, -74.0545997) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(location).title("El cielo"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))

        val HarrySasson = LatLng(4.659312421826357, -74.05457768165574) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(HarrySasson).title("Harry Sasson"))

        val AndrésCarnedeRes = LatLng(4.854371584087778, -74.06540323264043) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(AndrésCarnedeRes).title("Andrés Carne de Res"))

        val Criterion = LatLng(4.651741762609061, -74.05601951263216) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Criterion).title("Criterion"))

        val Mistura = LatLng(4.7248566849671025, -74.053616786269) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Mistura).title("Mistura"))

        val Minimal = LatLng(4.642501941984602, -74.06033257617905) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Minimal).title("Mini-mal"))

        val Leo = LatLng(4.64804473792112, -74.05643815773897) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Leo).title("Leo Cocina y Cava"))

        val Restaurante1621 = LatLng(410.4287790566288, -75.54828916173062) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Restaurante1621).title("Restaurante 1621"))

        val SalvoPatria = LatLng(4.6435524053586335, -74.0601029005077) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(SalvoPatria).title("Salvo Patria"))

        val Provincia = LatLng(4.796805321339548, -74.11266113726366) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Provincia).title("La Provincia"))

        val SantisimoCarbon = LatLng(4.747017858328193, -74.02959290749932) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(SantisimoCarbon).title("Santisimo Carbon"))

        val LaVitrola = LatLng(10.42548255526143, -75.55100877653803) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(LaVitrola).title("La Vitrola"))

        val Carmen = LatLng(10.391051673093653, -75.47936717927864) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Carmen).title("Carmen"))

        val Osaka = LatLng(4.6695690677584345, -74.05367849108788) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Osaka).title("Osaka"))

        val ElKilo = LatLng(4.650798665908019, -74.05720476590959) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(ElKilo).title("El Kilo"))

        val Barcal = LatLng(6.208759028515478, -75.57211625769888) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Barcal).title("Barcal"))

        val Original = LatLng(4.653082956293918, -74.05510230379248) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Original).title("Versión Original"))

        val BastionBistro = LatLng(10.42837055228262, -75.54584007577178) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(BastionBistro).title("Bastion Bistro"))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}