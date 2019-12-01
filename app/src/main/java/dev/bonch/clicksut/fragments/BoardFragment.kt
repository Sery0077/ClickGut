package dev.bonch.clicksut.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.ui.fragments.AddPostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import dev.bonch.clicksut.R
import dev.bonch.clicksut.activities.MainActivity
import dev.bonch.clicksut.models.Post
import dev.bonch.clicksut.ui.PostAdapter
import kotlinx.android.synthetic.main.fragment_add_post.*

class BoardFragment: Fragment() {

    private lateinit var fabAdd: FloatingActionButton
    private lateinit var dataBase: FirebaseFirestore
    private lateinit var mRef: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase

    private lateinit var recyclerView: RecyclerView

    private lateinit var posts: ArrayList<Post>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_board, container, false)

        dataBase = FirebaseFirestore.getInstance()

        getPosts()

        recyclerView = view.findViewById<RecyclerView>(R.id.boardRecyclerView)

        posts = ArrayList<Post>()

        recyclerView.adapter = PostAdapter(posts)
        recyclerView.layoutManager = LinearLayoutManager(BoardFragment@context, LinearLayoutManager.VERTICAL, false)


        fabAdd = view.findViewById(R.id.fab)
        fabAdd.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frag_container, AddPostFragment())
                .addToBackStack("BoardFragment")
                .commit()
        }

        return view
    }


    private fun getPosts() {
        dataBase.collection("lesnoe")
            .get()
            .addOnSuccessListener { it ->
                it.forEach {
                    posts.add(Post(it.get("description").toString(), it.get("userName").toString(), it.get("postImage").toString()))
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
            }
    }
}