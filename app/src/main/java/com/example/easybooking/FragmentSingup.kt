package com.example.easybooking

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class FragmentSingup : Fragment() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userEditText: EditText
    private lateinit var ciudadEditText: EditText
    private lateinit var registrarButton: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_singup, container, false)
        emailEditText = view.findViewById(R.id.Email)
        userEditText = view.findViewById(R.id.user)
        passwordEditText = view.findViewById(R.id.password)
        ciudadEditText = view.findViewById(R.id.ciudad)
        registrarButton = view.findViewById(R.id.Registrar)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrarButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val user = userEditText.text.toString()
            val ciudad = ciudadEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() || user.isEmpty() || ciudad.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Crear usuario en Firebase Authentication
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser = auth.currentUser
                            val userId = firebaseUser?.uid

                            // Guardar datos del usuario en Realtime Database
                            userId?.let { uid ->
                                val userRef = database.reference.child("users").child(uid)
                                val userData = hashMapOf(
                                    "email" to email,
                                    "user" to user,
                                    "ciudad" to ciudad
                                )
                                userRef.setValue(userData)
                                    .addOnSuccessListener {
                                        Toast.makeText(requireContext(), "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                                        // Navegar a la pantalla de inicio
                                        findNavController().navigate(R.id.action_fragmentSingup_to_homeFragment)
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(requireContext(), "Error al registrar usuario: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        } else {
                            Toast.makeText(requireContext(), "Error al registrar usuario: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}