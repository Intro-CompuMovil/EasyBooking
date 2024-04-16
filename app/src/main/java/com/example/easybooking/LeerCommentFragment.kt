package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class LeerCommentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leer_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the comment text (you can replace this with your actual comment data)
        val commentText = "This is a sample comment text."

        // Find the commentTextView and set its text
        val commentTextView = view.findViewById<TextView>(R.id.commentTextView)
        commentTextView.text = commentText
    }
}
