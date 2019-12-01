package bonch.dev.school.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.bonch.clicksut.R
import dev.bonch.clicksut.activities.MainActivity
import dev.bonch.clicksut.fragments.BoardFragment

class AddPostFragment: Fragment() {

    private lateinit var titltEt: EditText
    private lateinit var descrEt: EditText
    private lateinit var cetSpinner: Spinner
    private lateinit var publishBtn: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: String

    private lateinit var dataBase: FirebaseFirestore
    private lateinit var currentCat: String

    private val category = arrayOf("Продать/отдать даром", "Одолжить на время", "Помощь")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        titltEt = view.findViewById(R.id.title)
        descrEt = view.findViewById(R.id.description)
        cetSpinner = view.findViewById(R.id.category_spinner)
        publishBtn = view.findViewById(R.id.publish)

        dataBase = FirebaseFirestore.getInstance()
        initView()

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser!!.uid

        return view
    }

    private fun initView() {

        val adapter = ArrayAdapter.createFromResource(
            AddPostFragment@context!!,
            R.array.category,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        cetSpinner.adapter = adapter

        cetSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentCat = category[0]
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentCat = category[position]
            }

        }
        publishBtn.setOnClickListener {
            publish()
        }
    }

    private fun publish() {

        val title = titltEt.text.toString()
        val description = descrEt.text.toString()

        if (description.isNotEmpty() && title.isNotEmpty()) {
            dataBase.collection("lesnoe").add(
                mapOf(
                    "title" to title,
                    "description" to description,
                    "author" to user,
                    "type" to currentCat
                )
            )
                .addOnFailureListener {
                    Toast.makeText(AddPostFragment@ context, "${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnSuccessListener {
                    (context as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frag_container, BoardFragment())
                        .commit()

                    Toast.makeText(AddPostFragment@context, "Объявление опубликовано", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(AddPostFragment@ context, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
        }

    }
}