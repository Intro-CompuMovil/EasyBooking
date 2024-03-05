package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class PagoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pago, container, false)

        // Obtener referencias a las vistas
        val titulo = view.findViewById<TextView>(R.id.textView)
        val info = view.findViewById<TextView>(R.id.textView2)
        val Npersonas = view.findViewById<TextView>(R.id.textView6)
        val personas = view.findViewById<TextView>(R.id.textView7)
        val fechas = view.findViewById<TextView>(R.id.textView5)
        val fecha = view.findViewById<TextView>(R.id.textView3)
        val metodopago = view.findViewById<TextView>(R.id.imageView3)
        val targeta = view.findViewById<TextView>(R.id.textView8)
        val total = view.findViewById<TextView>(R.id.textView9)
        val efectivo = view.findViewById<TextView>(R.id.textView10)
        val pagarButton = view.findViewById<Button>(R.id.button2)

        // Configurar el texto de las vistas (Aquí puedes establecer la información que deseas mostrar)
        titulo.text = "Confirmar Reserva"
        info.text = "Tu reserva"
        Npersonas.text = "1"
        personas.text = "Personas"
        fechas.text = "12 marzo"
        fecha.text = "Fechas"
        metodopago.text = "Pago con"
        targeta.text = "Tarjeta Crédito *****4789"
        total.text = "Total"
        efectivo.text = "500.000"

        // Configurar clic del botón de pagar
        pagarButton.setOnClickListener {
            // Mostrar un toast
            Toast.makeText(requireContext(), "¡Muchas gracias! Procederemos al pago.", Toast.LENGTH_SHORT).show()
        }

        return view
    }
    }


