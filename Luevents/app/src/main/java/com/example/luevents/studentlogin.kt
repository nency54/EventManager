package com.example.luevents

import android.content.Intent
import android.os.Bundle
import android.os.TokenWatcher
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_newstdregister.*
import kotlinx.android.synthetic.main.activity_studentlogin.*
import kotlin.concurrent.thread


class studentlogin : AppCompatActivity() {

   //internal lateinit var db:db_for_stud
    private lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentlogin)
        mAuth = FirebaseAuth.getInstance()

        //  db = db_for_stud(this)



        std_login.setOnClickListener{


            val email = loginstud_id.text.toString().trim()
            val password = login_password.text.toString().trim()

            if (email.isEmpty()) {
                loginstud_id.error = "Email id is Required"
                loginstud_id.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                loginstud_id.error = "Valid Email Required"
                loginstud_id.requestFocus()
                return@setOnClickListener
            }

            loginUser(email,password)

        }



        new_student.setOnClickListener{
            Toast.makeText(this,"Welcome, Please Register", Toast.LENGTH_LONG).show()
            intent= Intent(this@studentlogin, newstdregister::class.java)
            startActivity(intent)
        }





    }

    private fun loginUser(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "WELCOME",Toast.LENGTH_LONG).show()

                    val intent =Intent(this@studentlogin,student_events::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)

                }
                else{
                    task.exception?.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }

            }



}}
