package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        btnenviar.setOnClickListener {
            Toast.makeText(requireContext(), "Se ha enviado ,Gracias Por tu comentario nos ayudas a crecer mas ", Toast.LENGTH_SHORT).show()
        }
        return view
    }


}