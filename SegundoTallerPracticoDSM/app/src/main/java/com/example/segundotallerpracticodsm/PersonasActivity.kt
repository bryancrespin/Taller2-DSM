package com.example.segundotallerpracticodsm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class PersonasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this@PersonasActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }, 9000) // espera 2 segundos antes de pasar a MainActivity

    }
}