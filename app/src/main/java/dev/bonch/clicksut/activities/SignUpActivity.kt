package dev.bonch.clicksut.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dev.bonch.clicksut.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEt: EditText
    private lateinit var lastNameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var confPassEt: EditText
    private lateinit var signUpBtn: Button
    private lateinit var vkLinkEd: EditText

    private lateinit var spinner: Spinner

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()

        signUpBtn.setOnClickListener {
            signUp()
        }
    }

    private fun initViews() {
        nameEt = findViewById(R.id.name)
        lastNameEt = findViewById(R.id.last_name)
        emailEt = findViewById(R.id.email)
        vkLinkEd = findViewById(R.id.vk_link)
        passEt = findViewById(R.id.pass)
        confPassEt = findViewById(R.id.conf_pass)
        signUpBtn = findViewById(R.id.sign_up_button)

        mDatabase = FirebaseDatabase.getInstance()
        mRef = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()


        spinner = findViewById(R.id.dom_spinner)
    }

    private fun signUp() {
        val name = nameEt.text.toString()
        val lastName = lastNameEt.text.toString()
        val email = emailEt.text.toString()
        val vkLink = vkLinkEd.text.toString()
        val pass = passEt.text.toString()
        val confPass = confPassEt.text.toString()

        if (pass == confPass && pass.isNotEmpty() && name.isNotEmpty() && lastName.isNotEmpty()) {
            mAuth
                .createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    var user = mAuth.currentUser!!.uid
                    mRef.child(user).child("name").setValue(name)
                    mRef.child(user).child("lastName").setValue(lastName)
                    mRef.child(user).child("vkLink").setValue(vkLink)

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        } else if (pass == confPass) {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
    }
}
