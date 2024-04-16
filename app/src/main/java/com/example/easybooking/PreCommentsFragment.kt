package com.example.easybooking



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class PreCommentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the NavController associated with this fragment
        val navController = findNavController()

        // Initialize buttons
        val button1 = view.findViewById<Button>(R.id.button1)
        val button2 = view.findViewById<Button>(R.id.button2)

        // Example: Navigate to ComentariosFragment
        button1.setOnClickListener {
            navController.navigate(R.id.action_preCommentsFragment_to_comentariosFragment)
        }

        // Example: Navigate to LeercomentariosFragment
        button2.setOnClickListener {
            navController.navigate(R.id.action_preCommentsFragment_to_LeercomentariosFragment)
        }
    }
}
