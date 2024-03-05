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


class FragmentSingup : Fragment() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userEditText: EditText
    private lateinit var ciudadEditText: EditText
    private lateinit var RegistarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_singup, container, false)
        emailEditText = view.findViewById(R.id.Email)
        userEditText  = view.findViewById(R.id.user)
        passwordEditText = view.findViewById(R.id.password)
        ciudadEditText = view.findViewById(R.id.ciudad)
        RegistarButton = view.findViewById(R.id.Registrar)
        // Configurar el mensaje de registro
        val registerMessage = view.findViewById<TextView>(R.id.mensajevuelta)
        val spannableString = SpannableString("¿ya tienes cuenta? Inicia")
        spannableString.setSpan(ForegroundColorSpan(Color.RED), 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(UnderlineSpan(), 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Acción al hacer clic en el texto "Regístrate"
                // Por ejemplo, abrir una nueva actividad de registro
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("URL_DE_TU_PAGINA_DE_REGISTRO"))
                startActivity(intent)
            }
        }, 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        registerMessage.movementMethod = LinkMovementMethod.getInstance()
        registerMessage.text = spannableString


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RegistarButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val user = userEditText.text.toString()
            val ciudad =ciudadEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() || user.isEmpty()||ciudad.isEmpty() ) {
                Toast.makeText(requireContext(), "Por favor, complete los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Si los campos están completos, navegar a la pantalla de inicio

            }
        }
        // Configurar el mensaje de registro
        val registerMessage = view.findViewById<TextView>(R.id.mensajevuelta)
        val spannableString = SpannableString("¿ya tienes cuenta? Inicia")
        spannableString.setSpan(ForegroundColorSpan(Color.RED), 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(UnderlineSpan(), 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Acción al hacer clic en el texto "Regístrate"
                // Navegar a FragmentSingup
                findNavController().navigate(R.id.action_fragmentSingup_to_fragmentLogin)
            }
        }, 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        registerMessage.movementMethod = LinkMovementMethod.getInstance()
        registerMessage.text = spannableString

    }

}