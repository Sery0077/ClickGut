package dev.bonch.clicksut.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.bonch.clicksut.R
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

        var name: String?
        var lastName: String?

//        mRef.child(post.userName).addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//            override fun onDataChange(p0: DataSnapshot) {
//                name = p0.child("name").getValue(String::class.java)
//                lastName = p0.child("lastName").getValue(String::class.java)
//
//                i
//                }
//            }

        // })

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