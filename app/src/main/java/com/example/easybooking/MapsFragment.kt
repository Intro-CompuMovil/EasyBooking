package com.example.easybooking

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
 class MapsFragment : Fragment(), OnMapReadyCallback {
     private lateinit var fusedLocationClient: FusedLocationProviderClient
     private lateinit var mMap: GoogleMap

     private val callback = OnMapReadyCallback { googleMap ->
         val blueMarkerIcon =
             BitmapDescriptorFactory.fromResource(R.drawable.baseline_bedroom_parent_24)


         val location = LatLng(
             4.6514381,
             -74.0545997
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(location).title("El cielo").snippet("4.8")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )
         googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))

         val HarrySasson = LatLng(
             4.659312421826357,
             -74.05457768165574
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(HarrySasson).title("Harry Sasson").snippet("4.6")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val AndrésCarnedeRes = LatLng(
             4.854371584087778,
             -74.06540323264043
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(AndrésCarnedeRes).title("Andrés Carne de Res").snippet("4.5")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Criterion = LatLng(
             4.651741762609061,
             -74.05601951263216
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Criterion).title("Criterion").snippet("4.7")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Mistura = LatLng(
             4.7248566849671025,
             -74.053616786269
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Mistura).title("Mistura").snippet("4.6")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Minimal = LatLng(
             4.642501941984602,
             -74.06033257617905
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Minimal).title("Mini-mal").snippet("4.5")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Leo = LatLng(
             4.64804473792112,
             -74.05643815773897
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Leo).title("Leo Cocina y Cava").snippet("4.8")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Restaurante1621 = LatLng(
             410.4287790566288,
             -75.54828916173062
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Restaurante1621).title("Restaurante 1621").snippet("4.7")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val SalvoPatria = LatLng(
             4.6435524053586335,
             -74.0601029005077
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(SalvoPatria).title("Salvo Patria").snippet("4.6")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Provincia = LatLng(
             4.796805321339548,
             -74.11266113726366
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Provincia).title("La Provincia").snippet("4.4")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val SantisimoCarbon = LatLng(
             4.747017858328193,
             -74.02959290749932
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(SantisimoCarbon).title("Santisimo Carbon").snippet("4.7")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val LaVitrola = LatLng(
             10.42548255526143,
             -75.55100877653803
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(LaVitrola).title("La Vitrola").snippet("4.6")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Carmen = LatLng(
             10.391051673093653,
             -75.47936717927864
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Carmen).title("Carmen").snippet("4.6")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Osaka = LatLng(
             4.6695690677584345,
             -74.05367849108788
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Osaka).title("Osaka").snippet("4.7")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val ElKilo = LatLng(
             4.650798665908019,
             -74.05720476590959
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(ElKilo).title("El Kilo").snippet("4.5")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Barcal = LatLng(
             6.208759028515478,
             -75.57211625769888
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Barcal).title("Barcal").snippet("4.6")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val Original = LatLng(
             4.653082956293918,
             -74.05510230379248
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Original).title("Versión Original").snippet("4.8")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val BastionBistro = LatLng(
             10.42837055228262,
             -75.54584007577178
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(BastionBistro).title("Bastion Bistro").snippet("4.7")
                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.restaurant_icon))
         )

         val HotelTermalesdelOtoño = LatLng(
             5.008669170725944,
             -75.44018242052084
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(HotelTermalesdelOtoño).title("Hotel Termales del Otoño").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )


         val Portón = LatLng(
             4.664558586137388,
             -74.04850299026671
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Portón).title("Hotel Portón Bogotá").snippet("5").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val CartagenaBeachResort = LatLng(
             10.371269556935491,
             -75.56647868833745
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(CartagenaBeachResort).title("Cartagena Beach Resort").snippet("5").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val MedellínDowntownSuites = LatLng(
             6.202540915490962,
             -75.57142345273303
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(MedellínDowntownSuites).title("Medellín Downtown Suites").snippet("4")
                 .icon(
                     bitmapDescriptorFromVector(
                         requireContext(),
                         R.drawable.baseline_bedroom_parent_24
                     )
                 )
         )

         val Oceanview = LatLng(
             11.200704373374228,
             -74.22722343216455
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Oceanview).title("Santa Marta Ocean view Hotel").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val CaliRiverSuites = LatLng(
             3.4514215654311338,
             -76.53873460689097
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(CaliRiverSuites).title("River Suites").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Portobelo = LatLng(
             12.585929707204897,
             -81.69672737560124
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Portobelo).title("Hotel Portobelo Beach").snippet("5").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Pereira = LatLng(
             4.738571578975511,
             -75.72585478326043
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Pereira).title("Pereira Mountain Lodge").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Barranquilla = LatLng(
             10.99862841448146,
             -74.80026474477769
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Barranquilla).title("Barranquilla Grand Plaza").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Leyva = LatLng(
             5.63284008104834,
             -73.52194878145588
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Leyva).title("Villa de Leyva Boutique Hotel").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Colonial = LatLng(
             2.442703988542523,
             -76.61023416183622
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Colonial).title("Colonial Retreat").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val QUO = LatLng(
             5.053500308274392,
             -75.48886874757315
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(QUO).title("QUO Quality Hotel").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Pasto = LatLng(
             1.2244324624198364,
             -77.28112718027657
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Pasto).title("Lakeside Resort").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Center = LatLng(
             7.925545828025628,
             -72.49315036031867
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Center).title("City Center Hotel").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Armenia = LatLng(
             4.54902424362554,
             -75.66017965340937
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Armenia).title("Vineyard Retreat").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Neiva = LatLng(
             2.960069739077076,
             -75.29747061931306
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Neiva).title("Desert Oasis").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Montería = LatLng(
             8.758619793206856,
             -75.88192093133453
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Montería).title("Riverside Hotel,").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Sincelejo = LatLng(
             9.30145482017214,
             -75.39531169415343
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Sincelejo).title("Urban Retreat").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Cid = LatLng(
             5.533733766720032,
             -73.36169085987493
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Cid).title("Mountain View Lodge").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Riohacha = LatLng(
             11.521127153261045,
             -72.96428599655498
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Riohacha).title("Coastal Resort").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Valledupar = LatLng(
             10.47989859881575,
             -73.24551989128823
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Valledupar).title("Cultural Hotel").snippet("3").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

         val Jones = LatLng(
             4.6459297302809865,
             -74.0602420344055
         ) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
         googleMap.addMarker(
             MarkerOptions().position(Jones).title("Suite Jones Hotel").snippet("4").icon(
                 bitmapDescriptorFromVector(
                     requireContext(),
                     R.drawable.baseline_bedroom_parent_24
                 )
             )
         )

     }

     private fun addPredefinedMarkers() {
         // Aquí puedes agregar tus marcadores predefinidos si los tienes
     }

     @SuppressLint("MissingPermission")
     private fun calculateDistanceToCurrentLocation(markerLatLng: LatLng) {
         fusedLocationClient.lastLocation.addOnSuccessListener { location ->
             location?.let {
                 val currentLatLng = LatLng(it.latitude, it.longitude)
                 val distance = calculateDistance(currentLatLng, markerLatLng)

                 val distanceText = "La distancia entre tu ubicación y el marcador es: $distance metros"
                 Toast.makeText(requireContext(), distanceText, Toast.LENGTH_LONG).show()
             }
         }
     }

     private fun calculateDistance(latlng1: LatLng, latlng2: LatLng): Double {
         // Esta función calcula la distancia entre dos puntos LatLng (en metros)
         val R = 6371 // Radio de la Tierra en kilómetros
         val latDistance = Math.toRadians(latlng2.latitude - latlng1.latitude)
         val lonDistance = Math.toRadians(latlng2.longitude - latlng1.longitude)
         val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                 (Math.cos(Math.toRadians(latlng1.latitude)) * Math.cos(Math.toRadians(latlng2.latitude)) *
                         Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2))
         val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
         val distance = R * c * 1000 // convertir a metros

         return distance
     }

     private fun bitmapDescriptorFromVector(
         context: Context,
         vectorResId: Int
     ): BitmapDescriptor {
         val vectorDrawable: Drawable? = ContextCompat.getDrawable(context, vectorResId)
         vectorDrawable?.setBounds(
             0,
             0,
             vectorDrawable.intrinsicWidth,
             vectorDrawable.intrinsicHeight
         )
         val bitmap = Bitmap.createBitmap(
             vectorDrawable!!.intrinsicWidth, vectorDrawable.intrinsicHeight,
             Bitmap.Config.ARGB_8888
         )
         val canvas = Canvas(bitmap)
         vectorDrawable.draw(canvas)
         return BitmapDescriptorFactory.fromBitmap(bitmap)
     }

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         return inflater.inflate(R.layout.fragment_maps, container, false)

     }


     companion object {
         private const val REQUEST_CODE_LOCATION_PERMISSION = 1
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         // Solicitar permisos de ubicación
         if (ActivityCompat.checkSelfPermission(
                 requireContext(),
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                 requireContext(),
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             ActivityCompat.requestPermissions(
                 requireActivity(),
                 arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                 REQUEST_CODE_LOCATION_PERMISSION
             )
         } else {
             // Si los permisos están concedidos, inicializar el mapa
             initializeMap()
         }

         fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
     }

     private fun initializeMap() {
         val mapFragment =
             childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
         mapFragment?.getMapAsync(callback)
         mapFragment?.getMapAsync(this)
     }


     @SuppressLint("MissingPermission")
     override fun onMapReady(googleMap: GoogleMap) {
         mMap = googleMap

         // Habilitar la capa de ubicación
         mMap.isMyLocationEnabled = true

         fusedLocationClient.lastLocation.addOnSuccessListener { location ->
             location?.let {
                 val currentLatLng = LatLng(it.latitude, it.longitude)
                 mMap.addMarker(
                     MarkerOptions()
                         .position(currentLatLng)
                         .title("Mi Ubicación")
                 )
                 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
             }
         }

         // Manejar clics en el mapa para agregar marcadores
         mMap.setOnMapClickListener { latLng ->
             mMap.addMarker(
                 MarkerOptions()
                     .position(latLng)
                     .title("Nuevo Marcador")
             )
             calculateDistanceToCurrentLocation(latLng)
         }
     }
 }




