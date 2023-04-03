package com.example.segundotallerpracticodsm

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.segundotallerpracticodsm.Persona2

class Ejemplo2Activity : AppCompatActivity() {
    // Ordenamiento para hacer la consultas a los datos
    var consultaOrdenada: Query = refPersonas.orderByChild("OpcionID2")
    var personas: MutableList<Persona2>? = null
    var listaPersonas: ListView? = null


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








    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona2)
        inicializar()


    }





    private fun inicializar() {
        val fab_agregar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab_agregar)
        listaPersonas = findViewById<ListView>(R.id.ListaPersonas)

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaPersonas!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val intent = Intent(getBaseContext(), AddPersona2Activity::class.java)
                intent.putExtra("accion", "e") // Editar
                intent.putExtra("key", personas!![i].key)
                intent.putExtra("nombre", personas!![i].nombre)
                intent.putExtra("OpcionID2", personas!![i].OpcionID2)
                intent.putExtra("sueldo", personas!![i].sueldo)
                intent.putExtra("tv2", personas!![i].tv2)
                startActivity(intent)
            }
        })

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaPersonas!!.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long
            ): Boolean {
                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(this@Ejemplo2Activity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"
                ) { dialog, id ->
                    personas!![position].nombre?.let {
                        refPersonas.child(it).removeValue()
                    }
                    Toast.makeText(
                        this@Ejemplo2Activity,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        Toast.makeText(
                            this@Ejemplo2Activity,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                return true
            }
        }
        fab_agregar.setOnClickListener(View.OnClickListener { // Cuando el usuario quiere agregar un nuevo registro
            val i = Intent(getBaseContext(), AddPersona2Activity::class.java)
            i.putExtra("accion", "a") // Agregar
            i.putExtra("key", "")
            i.putExtra("nombre", "")
            i.putExtra("OpcionID2", "")
            i.putExtra("sueldo", "")
            i.putExtra("tv2", "")
            startActivity(i)
        })
        personas = ArrayList<Persona2>()

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                personas!!.removeAll(personas!!)
                for (dato in dataSnapshot.getChildren()) {
                    val persona: Persona2? = dato.getValue(Persona2::class.java)
                    persona?.key(dato.key)
                    if (persona != null) {
                        personas!!.add(persona)
                    }
                }
                val adapter = AdaptadorPersona2(
                    this@Ejemplo2Activity,
                    personas as ArrayList<Persona2>
                )
                listaPersonas!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refPersonas: DatabaseReference = database.getReference("personas")
    }
}