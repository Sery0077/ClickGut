package dev.bonch.clicksut.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dev.bonch.clicksut.R

class SignInActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signInBtn: Button

    private lateinit var textSignUpTw: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()


        val user = mAuth.currentUser

        if (user !== null) {

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        textSignUpTw.setOnClickListener {
            SignUpActivityStart()
        }

        signInBtn.setOnClickListener {
            SignIn()
        }
    }

    private fun initView() {
        emailEt = findViewById(R.id.sign_in_email)
        passwordEt = findViewById(R.id.sign_in_password)
        signInBtn = findViewById(R.id.sign_in_button)
        textSignUpTw = findViewById(R.id.sign_in_sign_up)

        mAuth = FirebaseAuth.getInstance()
    }

    private fun SignUpActivityStart() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun SignIn() {
        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }

                .addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }

        } else Toast.makeText(this, "Введите почту и пароль", Toast.LENGTH_LONG).show()
    }
}
