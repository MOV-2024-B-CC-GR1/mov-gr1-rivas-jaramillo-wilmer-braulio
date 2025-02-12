package com.example.ccgr12024b_wbrj

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AgregarEstacionamientoActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_estacionamiento)

        databaseHelper = DatabaseHelper(this)

        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtUbicacion = findViewById<EditText>(R.id.edtUbicacion)
        val edtDescripcion = findViewById<EditText>(R.id.edtDescripcion)
        val edtPrecio = findViewById<EditText>(R.id.edtPrecio)
        val edtLatitud = findViewById<EditText>(R.id.edtLatitud)
        val edtLongitud = findViewById<EditText>(R.id.edtLongitud)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarEstacionamiento)

        btnGuardar.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val ubicacion = edtUbicacion.text.toString()
            val descripcion = edtDescripcion.text.toString()
            val precio = edtPrecio.text.toString().toDoubleOrNull() ?: 0.0
            val latitud = edtLatitud.text.toString().toDoubleOrNull() ?: 0.0
            val longitud = edtLongitud.text.toString().toDoubleOrNull() ?: 0.0

            if (nombre.isNotEmpty() && ubicacion.isNotEmpty() && descripcion.isNotEmpty() && precio > 0) {
                val insertado = databaseHelper.insertarEstacionamiento(nombre, ubicacion, descripcion, precio, latitud, longitud, R.drawable.estacionamiento_logo)
                if (insertado) {
                    Toast.makeText(this, "Estacionamiento agregado correctamente", Toast.LENGTH_SHORT).show()
                    finish() // Cierra la actividad después de agregar
                } else {
                    Toast.makeText(this, "Error al agregar estacionamiento", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}