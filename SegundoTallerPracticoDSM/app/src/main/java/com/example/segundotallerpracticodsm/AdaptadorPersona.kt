package com.example.segundotallerpracticodsm

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.segundotallerpracticodsm.Persona

class AdaptadorPersona(private val context: Activity, var personas: List<Persona>) :
    ArrayAdapter<Persona?>(context, R.layout.persona_layout, personas) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        rowview = view ?: layoutInflater.inflate(R.layout.persona_layout, null)


        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvOpcionID = rowview!!.findViewById<TextView>(R.id.tvOpcionID)
        val tvnota1 = rowview.findViewById<TextView>(R.id.tvnota1)
        val tvnota2 = rowview.findViewById<TextView>(R.id.tvnota2)
        val tvnota3 = rowview.findViewById<TextView>(R.id.tvnota3)
        val tvnota4 = rowview.findViewById<TextView>(R.id.tvnota4)
        val tvnota5 = rowview.findViewById<TextView>(R.id.tvnota5)
        val tv1 = rowview.findViewById<TextView>(R.id.tv1)

        tvOpcionID.text = "opcion : " + personas[position].OpcionID
        tvNombre.text = "Nombre : " + personas[position].nombre
        tvnota1.text = "NOTA1 : " +  personas[position].nota1
        tvnota2.text = "NOTA2 : " +  personas[position].nota2
        tvnota3.text = "NOTA3 : " +  personas[position].nota3
        tvnota4.text = "NOTA4 : " +  personas[position].nota4
        tvnota5.text = "NOTA5 : " +  personas[position].nota5
        tv1.text = "" +  personas[position].tv1

        return rowview
    }
}