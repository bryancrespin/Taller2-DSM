package com.example.segundotallerpracticodsm

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actio_sign_out ->{
                FirebaseAuth.getInstance().signOut().also{
                    Toast.makeText(this, "sesion cerrada", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()

                }

            }


            R.id.actio_sign_out1 ->{
                FirebaseAuth.getInstance().signOut().also{
                    Toast.makeText(this, "Promedio Alumno", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, EjemploActivity::class.java)
                    startActivity(intent)
                    finish()

                }

            }





            R.id.actio_sign_out3 ->{
                FirebaseAuth.getInstance().signOut().also{
                    Toast.makeText(this, "Salario", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, Ejemplo2Activity::class.java)
                    startActivity(intent)
                    finish()

                }

            }



        }

        return super.onOptionsItemSelected(item)
    }

}




















