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
import com.google.firebase.auth.FirebaseAuth
import dev.bonch.clicksut.R
import dev.bonch.clicksut.activities.SignInActivity
import kotlin.math.sign

class ProfileFragment: Fragment() {

    private lateinit var fullNameTw: TextView
    private lateinit var vkLinkTw: TextView

    private lateinit var avatarIv: ImageView
    private lateinit var signOutBtn: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cabinet, container, false)
        mAuth = FirebaseAuth.getInstance()
        signOutBtn = view.findViewById(R.id.sign_out_button)

        signOutBtn.setOnClickListener{
            mAuth.signOut()

            val intent = Intent(MainAppActivity@context, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return view
    }
}