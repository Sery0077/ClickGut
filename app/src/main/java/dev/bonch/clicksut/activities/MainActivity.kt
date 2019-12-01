package dev.bonch.clicksut.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dev.bonch.clicksut.R
import dev.bonch.clicksut.fragments.BoardFragment
import dev.bonch.clicksut.fragments.ChatFragment
import dev.bonch.clicksut.fragments.NewsFragment
import dev.bonch.clicksut.fragments.ProfileFragment
import dev.bonch.clicksut.models.Post
import dev.bonch.clicksut.ui.PostAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var botNavBottom: BottomNavigationView
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val user = mAuth.currentUser

        if (user == null) {

            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        init()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frag_container, BoardFragment())
            .commit()

    }

    private fun init() {
        botNavBottom = findViewById(R.id.bottom_navigation)

        botNavBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_news ->
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frag_container, NewsFragment())
                        .disallowAddToBackStack()
                        .commit()

                R.id.action_home ->
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frag_container, BoardFragment())

                        .commit()

                R.id.action_profile ->
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frag_container, ProfileFragment())

                        .commit()

                R.id.action_chat ->
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frag_container, ChatFragment())

                        .commit()
            }
            true
        }
    }
}
