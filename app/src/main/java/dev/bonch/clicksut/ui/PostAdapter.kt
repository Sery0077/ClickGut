package dev.bonch.clicksut.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import dev.bonch.clicksut.R
import dev.bonch.clicksut.activities.MainActivity
import dev.bonch.clicksut.models.Post


class PostAdapter(val postsList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val mDatabase = FirebaseDatabase.getInstance()
    private val mRef = mDatabase.reference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = postsList[position]

        holder.postDescription.text = post.description
        holder.userName.text = post.userName
        if (post.postImage == "") {
            holder.postImage.setImageResource(R.drawable.simple_photo)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postImage = itemView.findViewById(R.id.post_image) as ImageView
        //var userImage = itemView.findViewById(R.id.user_image) as ImageView
        val userName = itemView.findViewById(R.id.user_name) as TextView
        val postDescription = itemView.findViewById(R.id.post_description) as TextView
    }

}