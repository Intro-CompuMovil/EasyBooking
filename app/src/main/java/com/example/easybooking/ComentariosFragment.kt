package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ComentariosFragment : Fragment() {

    private lateinit var btnEnviar: Button
    private lateinit var editTextComentario: EditText
    private lateinit var spinnerCalificaciones: Spinner
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar Firebase Auth y Database Reference
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("comentarios")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comentarios, container, false)
        btnEnviar = view.findViewById(R.id.btnEnviarComentario)
        editTextComentario = view.findViewById(R.id.etComentario)
        spinnerCalificaciones = view.findViewById(R.id.spinnerCalificacion)

        val opcionesCalificaciones = resources.getStringArray(R.array.Calificaciones)
        val adapterCalificaciones = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opcionesCalificaciones)
        adapterCalificaciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCalificaciones.adapter = adapterCalificaciones

        // Manejar la selección del Spinner
        spinnerCalificaciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val calificacionSeleccionada = parent.getItemAtPosition(position).toString()
                if (calificacionSeleccionada == getString(R.string.calificanos)) {
                    // No hacer nada si se selecciona el mensaje predeterminado
                    return
                }
                // Mostrar un mensaje Toast con la calificación seleccionada
                Toast.makeText(requireContext(), "Calificación seleccionada: $calificacionSeleccionada", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireContext(), "Tu opinión nos ayuda mucho a mejorar", Toast.LENGTH_SHORT).show()
            }
        }

        btnEnviar.setOnClickListener {
            val comentario = editTextComentario.text.toString().trim()
            val calificacionPosition = spinnerCalificaciones.selectedItemPosition

            if (calificacionPosition == 0) { // La primera posición es la del texto "Califícanos"
                Toast.makeText(requireContext(), "Por favor, seleccione una calificación válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (comentario.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, ingrese un comentario válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = auth.currentUser
            val userId = user?.uid ?: "Anonymous"
            val userEmail = user?.email ?: "Anonymous"

            val comentarioId = database.push().key

            val calificacion = spinnerCalificaciones.getItemAtPosition(calificacionPosition).toString()

            val comentarioData = mapOf(
                "userId" to userId,
                "userEmail" to userEmail, // Guardar el email del usuario
                "comentario" to comentario,
                "calificacion" to calificacion,
                "timestamp" to System.currentTimeMillis()
            )

            comentarioId?.let {
                database.child(it).setValue(comentarioData)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Comentario enviado exitosamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Error al enviar el comentario", Toast.LENGTH_SHORT).show()
                    }
            }
        }


        return view
    }
}