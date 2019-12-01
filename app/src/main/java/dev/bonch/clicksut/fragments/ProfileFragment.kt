package dev.bonch.clicksut.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.bonch.clicksut.R
import dev.bonch.clicksut.activities.SignInActivity
import dev.bonch.clicksut.models.Post
import dev.bonch.clicksut.ui.PostAdapter
import kotlin.math.sign

class ProfileFragment : Fragment() {

    private lateinit var fullNameTw: TextView
    private lateinit var vkLinkTw: TextView
    private lateinit var dataBase: FirebaseFirestore

    private lateinit var avatarIv: ImageView
    private lateinit var signOutBtn: Button
    private lateinit var mAuth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView

    private lateinit var posts: ArrayList<Post>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cabinet, container, false)
        mAuth = FirebaseAuth.getInstance()
        signOutBtn = view.findViewById(R.id.sign_out_button)

        dataBase = FirebaseFirestore.getInstance()

        getPosts()

        recyclerView = view.findViewById<RecyclerView>(R.id.boardRecyclerView)

        posts = ArrayList<Post>()

        recyclerView.adapter = PostAdapter(posts)
        recyclerView.layoutManager =
            LinearLayoutManager(BoardFragment@ context, LinearLayoutManager.VERTICAL, false)

        signOutBtn.setOnClickListener {
            mAuth.signOut()

            val intent = Intent(MainAppActivity@ context, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }

    private fun getPosts() {
        dataBase.collection("lesnoe")
            .get()
            .addOnSuccessListener { it ->
                it.forEach {
                    if (it.get("userName").toString() == "Сергей") {
                        posts.add(
                            Post(
                                it.get("description").toString(),
                                it.get("userName").toString(),
                                it.get("postImage").toString()
                            )
                        )
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                }
            }
    }
}