package com.example.segundotallerpracticodsm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.example.segundotallerpracticodsm.Persona

class AddPersonaActivity : AppCompatActivity() {
    private var tvOPCIONID: EditText? = null
    private var edtNombre: EditText? = null
    private var edtNOTA1: EditText? = null
    private var edtNOTA2: EditText? = null
    private var edtNOTA3: EditText? = null
    private var edtNOTA4: EditText? = null
    private var edtNOTA5: EditText? = null
    private var TV1: TextView? = null
    private var key = ""
    private var tvOpcionID = ""
    private var nombre = ""
    private var nota1 = ""
    private var nota2 = ""
    private var nota3 = ""
    private var nota4 = ""
    private var nota5 = ""
    private var tv1 = ""
    private var accion = ""
    private lateinit var  database: DatabaseReference

    lateinit var tvopcion1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alumno)
        inicializar()

        val et1=findViewById<EditText>(R.id.edtnota1)
        val et2=findViewById<EditText>(R.id.edtnota2)
        val et3=findViewById<EditText>(R.id.edtnota3)
        val et4=findViewById<EditText>(R.id.edtnota4)
        val et5=findViewById<EditText>(R.id.edtnota5)
        val tv1=findViewById<TextView>(R.id.tv1)


        val button=findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val et1=findViewById<EditText>(R.id.edtnota1)
            val nro1 = et1.text.toString().toInt()
            val nro2 = et2.text.toString().toInt()
            val nro3 = et3.text.toString().toInt()
            val nro4 = et4.text.toString().toInt()
            val nro5 = et5.text.toString().toInt()

            val promedio = (nro1 + nro2 + nro3 +  nro4 +  nro5)/5


            if(promedio<=6){

                tv1.text = "Nota Promedio: ${promedio.toString()} Reprobado"


            }
            else{

                tv1.text = "Nota Promedio: ${promedio.toString()} Aprobado"

            }
        }


    }




    private fun inicializar() {
        tvOPCIONID= findViewById<EditText>(R.id.tvOpcionID)
        edtNombre = findViewById<EditText>(R.id.edtNombre)
        edtNOTA1 = findViewById<EditText>(R.id.edtnota1 )
        edtNOTA2 = findViewById<EditText>(R.id.edtnota2 )
        edtNOTA3 = findViewById<EditText>(R.id.edtnota3 )
        edtNOTA4 = findViewById<EditText>(R.id.edtnota4 )
        edtNOTA5 = findViewById<EditText>(R.id.edtnota5 )
        TV1 = findViewById<TextView>(R.id.tv1 )

        val tvOpcionID = findViewById<TextView>(R.id.tvOpcionID)
        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtnota1 = findViewById<EditText>(R.id.edtnota1)
        val edtnota2 = findViewById<EditText>(R.id.edtnota2)
        val edtnota3 = findViewById<EditText>(R.id.edtnota3)
        val edtnota4 = findViewById<EditText>(R.id.edtnota4)
        val edtnota5 = findViewById<EditText>(R.id.edtnota5)
        val tv1 = findViewById<TextView>(R.id.tv1)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }

        if (datos != null) {
            tvOpcionID.setText(intent.getStringExtra("tvOpcionID").toString())
        }
        if (datos != null) {
            edtNombre.setText(intent.getStringExtra("nombre").toString())
        }
        if (datos != null) {
            edtnota1.setText(intent.getStringExtra("nota1").toString())
        }
        if (datos != null) {
            edtnota2.setText(intent.getStringExtra("nota2").toString())
        }
        if (datos != null) {
            edtnota3.setText(intent.getStringExtra("nota3").toString())
        }
        if (datos != null) {
            edtnota4.setText(intent.getStringExtra("nota4").toString())
        }
        if (datos != null) {
            edtnota5.setText(intent.getStringExtra("nota5").toString())
        }
        if (datos != null) {
            tv1.setText(intent.getStringExtra("tv1").toString())
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }

    }


    fun guardar(v: View?) {
        val tvOpcionID: String = tvOPCIONID?.text.toString()
        val nombre: String = edtNombre?.text.toString()
        val nota1: String = edtNOTA1?.text.toString()
        val nota2: String = edtNOTA2?.text.toString()
        val nota3: String = edtNOTA3?.text.toString()
        val nota4: String = edtNOTA4?.text.toString()
        val nota5: String = edtNOTA5?.text.toString()
        val tv1: String = TV1?.text.toString()

        database=FirebaseDatabase.getInstance().getReference("personas")

        // Se forma objeto persona
        val persona = Persona(tvOpcionID,nombre,nota1,nota2,nota3,nota4,nota5,tv1)

        if (accion == "a") { //Agregar registro
            database.child(nombre).setValue(persona).addOnSuccessListener {
                Toast.makeText(this,"Se guardo con exito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed ", Toast.LENGTH_SHORT).show()
            }
        } else  // Editar registro
        {
            val key = database.child("nombre").push().key
            if (key == null) {
                Toast.makeText(this,"Llave vacia", Toast.LENGTH_SHORT).show()
            }
            val personasValues = persona.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$nombre" to personasValues
            )
            database.updateChildren(childUpdates)
            Toast.makeText(this,"Se actualizo con exito", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    fun cancelar(v: View?) {
        finish()
    }
}
