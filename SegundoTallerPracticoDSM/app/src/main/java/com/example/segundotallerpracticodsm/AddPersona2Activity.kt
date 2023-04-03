package com.example.segundotallerpracticodsm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.example.segundotallerpracticodsm.Persona2

class AddPersona2Activity : AppCompatActivity() {


    private var tvOPCIONID2: EditText? = null
    private var edtNombre: EditText? = null
    private var edtSUELDO: EditText? = null
    private var TV2: TextView? = null
    private var key = ""
    private var nombre = ""
    private var tvOpcionID2 = ""
    private var sueldo = ""
    private var tv2 = ""
    private var accion = ""
    private lateinit var  database: DatabaseReference

    lateinit var tvopcion1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_persona2)
        inicializar()

        val et2=findViewById<EditText>(R.id.edtsueldo)
        val tv2=findViewById<TextView>(R.id.tv2)
        val button=findViewById<Button>(R.id.button)
        val spinner=findViewById<Spinner>(R.id.spinner)

        val lista = arrayOf("ISSS", "AFP", "RENTA")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador1

        button.setOnClickListener {
            when (spinner.selectedItem.toString()) {
                "ISSS" -> tv2.text = "Sueldo Neto Con ISSS: ${et2.text.toString().toInt() - (et2.text.toString().toInt())*(0.03)}"
                "AFP" -> tv2.text = "Sueldo Neto Con AFP: ${et2.text.toString().toInt() - (et2.text.toString().toInt())*(0.04)}"
                "RENTA" -> tv2.text = "Sueldo Neto Con Renta: ${et2.text.toString().toInt() - (et2.text.toString().toInt())*(0.05)}"

            }
        }

    }




    private fun inicializar() {
        edtNombre = findViewById<EditText>(R.id.edtNombre)
        tvOPCIONID2= findViewById<EditText>(R.id.tvOpcionID2)
        edtSUELDO = findViewById<EditText>(R.id.edtsueldo )

        TV2 = findViewById<TextView>(R.id.tv2 )


        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val tvOpcionID2 = findViewById<TextView>(R.id.tvOpcionID2)
        val sueldo = findViewById<EditText>(R.id.edtsueldo)

        val tv2 = findViewById<TextView>(R.id.tv2)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }

        if (datos != null) {
            tvOpcionID2.setText(intent.getStringExtra("tvOpcionID2").toString())
        }
        if (datos != null) {
            edtNombre.setText(intent.getStringExtra("nombre").toString())
        }
        if (datos != null) {
            sueldo.setText(intent.getStringExtra("sueldo").toString())
        }
        if (datos != null) {
            tv2.setText(intent.getStringExtra("tv2").toString())
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }

    }


    fun guardar(v: View?) {

        val nombre: String = edtNombre?.text.toString()
        val tvOpcionID2: String = tvOPCIONID2?.text.toString()
        val sueldo: String = edtSUELDO?.text.toString()

        val tv2: String = TV2?.text.toString()

        database=FirebaseDatabase.getInstance().getReference("personas")

        // Se forma objeto persona
        val persona = Persona2(tvOpcionID2,nombre,sueldo,tv2)

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
