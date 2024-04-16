package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class ComentariosFragment : Fragment() {

private lateinit var btnenviar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_comentarios, container, false)
        btnenviar= view.findViewById(R.id.btnEnviarComentario)

        val spinnerCalificaciones = view.findViewById<Spinner>(R.id.spinnerCalificacion)
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
                // Establecer la opción predeterminada nuevamente después de la selección
                spinnerCalificaciones.setSelection(0)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireContext(), "tu opinion nos ayuda mucho a mejorar  ", Toast.LENGTH_SHORT).show()
            }
        }


        btnenviar.setOnClickListener {
            Toast.makeText(requireContext(), "Se ha enviado ,Gracias Por tu comentario nos ayudas a crecer mas ", Toast.LENGTH_SHORT).show()
        }
        return view
    }


}