package com.example.easybooking

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

private const val PICK_IMAGE_REQUEST = 1
private const val CAMERA_REQUEST_CODE = 2

class PerfilFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userCountry: TextView
    private lateinit var userCorreo: TextView
    private lateinit var fabCamera: FloatingActionButton
    private lateinit var btnSaveChanges: Button
    private lateinit var btnLogout: Button

    private lateinit var database: DatabaseReference
    private lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
        storage = FirebaseStorage.getInstance()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        profileImage = view.findViewById(R.id.profile_image)
        userName = view.findViewById(R.id.user_id)
        userCountry = view.findViewById(R.id.user_country)
        userCorreo = view.findViewById(R.id.user_correo)
        fabCamera = view.findViewById(R.id.fab_camera)
        btnSaveChanges = view.findViewById(R.id.btn_save_changes)
        btnLogout = view.findViewById(R.id.btn_logout)

        loadUserData()

        fabCamera.setOnClickListener {
            openCameraOrGallery()
        }

        btnSaveChanges.setOnClickListener {
            showEditProfileDialog()
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_perfilFragment_to_fragmentLogin)
        }

        return view
    }

    private fun loadUserData() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val userId = user.uid
            val userRef = database.child("users").child(userId)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userData = dataSnapshot.getValue(Users::class.java)
                    userData?.let {
                        userName.text = it.user
                        userCountry.text = it.ciudad
                        userCorreo.text = it.email

                        val profileImageUrl = it.profileImage
                        if (profileImageUrl.isNotEmpty()) {
                            Glide.with(requireContext())
                                .load(profileImageUrl)
                                .placeholder(R.drawable.img_3)
                                .error(R.drawable.img_4)
                                .into(profileImage)
                        } else {
                            profileImage.setImageResource(R.drawable.img_4)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun showEditProfileDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_profile, null)
        dialog.setView(dialogView)
        val editTextNames = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextCountrys = dialogView.findViewById<EditText>(R.id.editTextCountry)
        val editTextEmails = dialogView.findViewById<EditText>(R.id.editTextEmail)

        editTextNames.setText(userName.text)
        editTextCountrys.setText(userCountry.text)
        editTextEmails.setText(userCorreo.text)

        dialog.setPositiveButton("Guardar") { _, _ ->
            val name = editTextNames.text.toString().trim()
            val country = editTextCountrys.text.toString().trim()
            val email = editTextEmails.text.toString().trim()

            if (name.isNotEmpty() && country.isNotEmpty() && email.isNotEmpty()) {
                saveChanges(name, country, email)
            } else {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.setNegativeButton("Cancelar", null)
        dialog.show()
    }

    private fun saveChanges(name: String, country: String, email: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val userId = user.uid
            val userRef = database.child("users").child(userId)
            val userData = Users(name, country, email, "")

            userRef.setValue(userData)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Cambios guardados", Toast.LENGTH_SHORT).show()
                    userName.text = name
                    userCountry.text = country
                    userCorreo.text = email
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Error al guardar cambios", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun uploadImageToFirebase(uri: Uri) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val userId = user.uid
            val storageRef = storage.reference.child("profile_images/$userId.jpg")
            val uploadTask = storageRef.putFile(uri)

            uploadTask.addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    if (isAdded) {
                        database.child("users").child(userId).child("profileImage").setValue(downloadUri.toString())
                        Glide.with(requireContext()).load(downloadUri).into(profileImage)
                        Toast.makeText(context, "Imagen actualizada", Toast.LENGTH_SHORT).show()
                    }
                }
            }.addOnFailureListener {
                if (isAdded) {
                    Toast.makeText(context, "Error al subir imagen", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openCameraOrGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    data?.data?.let { uri ->
                        uploadImageToFirebase(uri)
                    }
                }
                CAMERA_REQUEST_CODE -> {
                    val imageBitmap = data?.extras?.get("data") as? Bitmap
                    imageBitmap?.let {
                        // Puedes convertir el bitmap a URI y subirlo a Firebase Storage
                    }
                }
            }
        }
    }
}
