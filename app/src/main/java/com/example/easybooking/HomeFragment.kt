package com.example.easybooking

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class HomeFragment : Fragment() {

    companion object {
        private const val CAMERA_REQUEST = 1
        private const val PERMISSION_REQUEST_CAMERA = 100
    }

    private lateinit var btnRestaurantList: Button
    private lateinit var btnHotelList: Button
    private lateinit var btnreservas: Button
    private lateinit var btnCamera: ImageButton
    private lateinit var btnComentario: Button
    private lateinit var btnMap: Button
    private lateinit var btneditarp: Button
    private var capturedImage: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val spinnerActividad = view.findViewById<Spinner>(R.id.Seleccionplan)
        val opciones = resources.getStringArray(R.array.planes)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerActividad.adapter = adapter

        spinnerActividad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Manejar la selección del usuario aquí
                val opcionSeleccionada = parent.getItemAtPosition(position).toString()
                if (opcionSeleccionada == getString(R.string.que_quieres_hacer)) {
                    // No hacer nada si se selecciona el mensaje predeterminado
                    return
                }
                // Redirigir al usuario al fragmento correspondiente según la opción seleccionada
                when (opcionSeleccionada) {
                    "Comer" -> findNavController().navigate(R.id.action_homeFragment_to_fragmentRestaurant_list)
                    "Descansar" -> findNavController().navigate(R.id.action_homeFragment_to_fragment_hotel_list)
                }
                spinnerActividad.setSelection(0)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireContext(), "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show()
            }
        }
        btneditarp = view.findViewById(R.id.btnEditProfile)
        btnCamera = view.findViewById(R.id.btnCamera)
        btnComentario = view.findViewById(R.id.btnComentario)
        btnMap = view.findViewById(R.id.btnMap)
        btnRestaurantList = view.findViewById(R.id.btnRestaurantList)
        btnHotelList = view.findViewById(R.id.btnHotelList)
        btnreservas = view.findViewById(R.id.btnreservas)

        btnRestaurantList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentRestaurant_list)
        }

        btnHotelList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragment_hotel_list)
        }

        btnComentario.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_comentariosFragment)
        }

        btnHotelList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragment_hotel_list)
        }

        btnreservas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bookingFragment)
        }

        btnMap.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsFragment)
        }
        btneditarp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
        }

        btnCamera.setOnClickListener {
            if (checkCameraPermission()) {
                startQRScanner()
            } else {
                requestCameraPermission()
            }
        }

        return view
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan a QR code")
        integrator.setCameraId(0)  // Use a specific camera of the device
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            PERMISSION_REQUEST_CAMERA
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startQRScanner()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permiso necesario")
            .setMessage("Los permisos de cámara son necesarios para capturar una foto. Por favor, habilítalos manualmente en la configuración de la aplicación.")
            .setPositiveButton("Ir a configuración") { dialog, _ ->
                dialog.dismiss()
                // Open app settings
                val intent =
                    Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:${requireActivity().packageName}")
                startActivity(intent)
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                // Validar y abrir la URL escaneada
                var url = result.contents
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://$url"
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
