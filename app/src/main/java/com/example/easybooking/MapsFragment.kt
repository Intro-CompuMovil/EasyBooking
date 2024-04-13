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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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

        val blueMarkerIcon = BitmapDescriptorFactory.fromResource(R.drawable.hotel)



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

        val HotelTermalesdelOtoño = LatLng(5.008669170725944, -75.44018242052084) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(HotelTermalesdelOtoño).title("Hotel Termales del Otoño"))

        val Portón = LatLng(4.664558586137388, -74.04850299026671) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Portón).title("Hotel Portón Bogotá"))

        val CartagenaBeachResort = LatLng(10.371269556935491, -75.56647868833745) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(CartagenaBeachResort).title("Cartagena Beach Resort"))

        val MedellínDowntownSuites = LatLng(6.202540915490962, -75.57142345273303) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(MedellínDowntownSuites).title("Medellín Downtown Suites"))

        val Oceanview = LatLng(11.200704373374228, -74.22722343216455) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Oceanview).title("Santa Marta Ocean view Hotel"))

        val CaliRiverSuites = LatLng(3.4514215654311338, -76.53873460689097) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(CaliRiverSuites).title("Cali River Suites\n"))

        val Portobelo = LatLng(12.585929707204897, -81.69672737560124) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Portobelo).title("Hotel Portobelo Beach"))

        val Pereira = LatLng(4.738571578975511, -75.72585478326043) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Pereira).title("Pereira Mountain Lodge"))

        val Barranquilla = LatLng(10.99862841448146, -74.80026474477769) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Barranquilla).title("Barranquilla Grand Plaza"))

        val Leyva = LatLng(5.63284008104834, -73.52194878145588) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Leyva).title("Villa de Leyva Boutique Hotel"))

        val Colonial = LatLng(6.208759028515478, -75.57211625769888) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Colonial).title("Popayán Colonial Retreat"))

        val QUO  = LatLng(5.053500308274392, -75.48886874757315) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(QUO).title("QUO Quality Hotel"))

        val Pasto = LatLng(1.2244324624198364, -77.28112718027657) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Pasto).title("Lakeside Resort"))

        val Center = LatLng(7.925545828025628, -72.49315036031867) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Center).title("City Center Hotel"))

        val Armenia = LatLng(4.54902424362554, -75.66017965340937) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Armenia).title("Vineyard Retreat"))

        val Neiva = LatLng(2.960069739077076, -75.29747061931306) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Neiva).title("Desert Oasis"))

        val Montería = LatLng(8.758619793206856, -75.88192093133453) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Montería).title("Riverside Hotel,"))

        val Sincelejo = LatLng(9.30145482017214, -75.39531169415343) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Sincelejo).title("Urban Retreat"))

        val Cid = LatLng(5.533733766720032, -73.36169085987493) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Cid).title("Mountain View Lodge"))

        val Riohacha = LatLng(11.521127153261045, -72.96428599655498) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Riohacha).title("Coastal Resort"))

        val Valledupar = LatLng(10.47989859881575, -73.24551989128823) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Valledupar).title("Cultural Hotel"))

        val Jones = LatLng(4.6459297302809865, -74.0602420344055) // Latitude and longitude for "Calle 70 #4-47, Cl. 70 #4 - 47, Bogotá"
        googleMap.addMarker(MarkerOptions().position(Jones).title("Suite Jones Hotel"))

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