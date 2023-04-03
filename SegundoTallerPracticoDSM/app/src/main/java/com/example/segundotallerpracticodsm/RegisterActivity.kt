package com.example.segundotallerpracticodsm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.content.res.Configuration
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var buttonRegister: Button
    private lateinit var textViewLogin: TextView

    private fun register(email:String, password:String){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }

        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }

    private fun goToLogin(){
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)

    }



    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(authStateListener)
    }

    private fun checkUser(){

        authStateListener=FirebaseAuth.AuthStateListener{auth ->


    }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        auth=FirebaseAuth.getInstance()

        buttonRegister= findViewById<Button>(R.id.btnRegister)
        buttonRegister.setOnClickListener{
            val email= findViewById<EditText>(R.id.txtEmail).text.toString()
            val password= findViewById<EditText>(R.id.txtPass).text.toString()
            this.register(email,password)

        }
        textViewLogin=findViewById<TextView>(R.id.textViewLogin)
        textViewLogin.setOnClickListener{
            this.goToLogin()
        }

        this.checkUser()
    }



}