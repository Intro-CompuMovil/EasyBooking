package com.example.easybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Comment

class LeerCommentFragment : Fragment() {

    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leer_comment, container, false)
        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView)
        commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().reference.child("comentarios")
        fetchComments()
    }

    private fun fetchComments() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val comments = ArrayList<Commet>()
                for (commentSnapshot in snapshot.children) {
                    val userId = commentSnapshot.child("userId").getValue(String::class.java) ?: ""
                    val userEmail = commentSnapshot.child("userEmail").getValue(String::class.java) ?: ""
                    val comentario = commentSnapshot.child("comentario").getValue(String::class.java) ?: ""
                    val calificacion = commentSnapshot.child("calificacion").getValue(String::class.java) ?: ""
                    val comment = Commet(userEmail, comentario, calificacion)
                    comments.add(comment)
                }
                commentsRecyclerView.adapter = CommentsAdapter(comments)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}


class CommentsAdapter(private val comments: List<Commet>) :
    RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val commentDetailsTextView: TextView = itemView.findViewById(R.id.commentDetailsTextView)

        fun bind(comment: Commet) {
            userNameTextView.text = comment.userEmail
            val commentDetails = "${comment.comentario} (Calificación: ${comment.calificacion})"
            commentDetailsTextView.text = commentDetails
        }
    }
}
