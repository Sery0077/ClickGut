package dev.bonch.clicksut.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dev.bonch.clicksut.R
import dev.bonch.clicksut.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var botNavBottom: BottomNavigationView
    private lateinit var mAuth: FirebaseAuth

    private var showPostFragment = ShowPostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    fun showPost() {
        showPostFragment.show(supportFragmentManager, "show_post")
    }
}
