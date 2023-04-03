package com.example.segundotallerpracticodsm

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.segundotallerpracticodsm.Persona2

class AdaptadorPersona2(private val context: Activity, var personas: List<Persona2>) :
    ArrayAdapter<Persona2?>(context, R.layout.persona2_layout, personas) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        rowview = view ?: layoutInflater.inflate(R.layout.persona2_layout, null)


        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvOpcionID2 = rowview!!.findViewById<TextView>(R.id.tvOpcionID2)
        val tvsueldo = rowview.findViewById<TextView>(R.id.tvsueldo)

        val tv2 = rowview.findViewById<TextView>(R.id.tv2)


        tvNombre.text = "Nombre : " + personas[position].nombre
        tvOpcionID2.text = "opcion2 : " + personas[position].OpcionID2
        tvsueldo.text = "Sueldo Base : " +  personas[position].sueldo
        tv2.text = "" +  personas[position].tv2

        return rowview
    }
}