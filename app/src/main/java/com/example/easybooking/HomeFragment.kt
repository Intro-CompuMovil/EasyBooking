package com.example.easybooking

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    companion object {
        private const val CAMERA_REQUEST = 1
        private const val PERMISSION_REQUEST_CAMERA = 100
    }


    private lateinit var btnRestaurantList: Button
    private lateinit var btnHotelList: Button
    private lateinit var btnreservas: Button
    private lateinit var btnCamera: ImageButton
    private var capturedImage: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnCamera = view.findViewById(R.id.btnCamera)

        btnRestaurantList = view.findViewById(R.id.btnRestaurantList)
        btnHotelList = view.findViewById(R.id.btnHotelList)

        btnreservas = view.findViewById(R.id.btnreservas)

        btnRestaurantList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentRestaurant_list)
        }

        btnHotelList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragment_hotel_list)
        }


        btnHotelList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragment_hotel_list)
        }

        btnreservas.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_bookingFragment)
        }






        btnCamera.setOnClickListener {
            if (checkCameraPermission()) {
                takePicture()
            } else {
                requestCameraPermission()
            }
        }


        return view
    }

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, CAMERA_REQUEST)
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
                takePicture()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permiso necesario")
            .setMessage("Los permisos de cámara son necesarios para capturar una foto. Por favor, habilítalos manualmente en la configuración de la aplicación.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val imageBitmap = extras?.get("data") as Bitmap?
            // Do something with the captured image
        }
    }


}

