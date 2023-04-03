package com.example.segundotallerpracticodsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.segundotallerpracticodsm.R
import com.google.firebase.auth.FirebaseAuth

class SalarioActivity : AppCompatActivity() {

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

                    val intent= Intent(this, SalarioActivity::class.java)
                    startActivity(intent)
                    finish()

                }

            }


        }

        return super.onOptionsItemSelected(item)
    }








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)


        val et2=findViewById<EditText>(R.id.et2)
        val tv1=findViewById<TextView>(R.id.tv1)
        val button=findViewById<Button>(R.id.button)
        val spinner=findViewById<Spinner>(R.id.spinner)

        val lista = arrayOf("ISSS", "AFP", "RENTA")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador1

        button.setOnClickListener {
            when (spinner.selectedItem.toString()) {
                "ISSS" -> tv1.text = "Resultado: ${et2.text.toString().toInt() - (et2.text.toString().toInt())*(0.03)}"
                "AFP" -> tv1.text = "Resultado: ${et2.text.toString().toInt() - (et2.text.toString().toInt())*(0.04)}"
                "RENTA" -> tv1.text = "Resultado: ${et2.text.toString().toInt() - (et2.text.toString().toInt())*(0.05)}"

            }
        }

    }

    }
