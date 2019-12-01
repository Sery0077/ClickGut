package dev.bonch.clicksut.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.bonch.clicksut.R

class ProfileFragment: Fragment() {

    private lateinit var fullNameTw: TextView
    private lateinit var vkLinkTw: TextView

    private lateinit var avatarIv: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cabinet, container, false)

        return view
    }
}