package com.example.luevents

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_newstdregister.*

class newstdregister : AppCompatActivity() {


    // internal lateinit var db:db_for_stud
    private lateinit var  mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newstdregister)

       // db=db_for_stud(this)
        mAuth = FirebaseAuth.getInstance()

        to_home.setOnClickListener {
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        register.setOnClickListener {



            val email = stud_id.text.toString().trim()
            val password = password.text.toString().trim()
           // val confirm = con_password.text.toString().trim()

            if (email.isEmpty()) {
                stud_id.error = "Email Required"
                stud_id.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                stud_id.error = "Valid Email Required"
                stud_id.requestFocus()
                return@setOnClickListener
            }


            registerUser(email,password)

        }
    }

    private fun registerUser(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->

                if(task.isSuccessful)
                    Toast.makeText(this@newstdregister, "User registered succesfully",Toast.LENGTH_LONG).show()
                else {
                    task.exception?.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }


            }


    }

}
