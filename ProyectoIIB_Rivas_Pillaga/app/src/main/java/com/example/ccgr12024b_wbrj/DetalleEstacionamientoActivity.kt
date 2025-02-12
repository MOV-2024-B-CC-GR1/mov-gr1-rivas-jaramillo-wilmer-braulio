package com.example.ccgr12024b_wbrj

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalleEstacionamientoActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_estacionamiento)

        databaseHelper = DatabaseHelper(this)

        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvUbicacion = findViewById<TextView>(R.id.tvUbicacion)
        val tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        val tvPrecio = findViewById<TextView>(R.id.tvPrecio)
        val btnSolicitar = findViewById<Button>(R.id.btnSolicitarEstacionamiento)

        // Obtener datos pasados desde la lista de estacionamientos
        val idEstacionamiento = intent.getIntExtra("ID_ESTACIONAMIENTO", -1)
        val nombre = intent.getStringExtra("NOMBRE") ?: "No disponible"
        val ubicacion = intent.getStringExtra("UBICACION") ?: "No disponible"
        val descripcion = intent.getStringExtra("DESCRIPCION") ?: "No disponible"
        val precio = intent.getDoubleExtra("PRECIO", 0.0)

        tvNombre.text = "Nombre: $nombre"
        tvUbicacion.text = "Ubicación: $ubicacion"
        tvDescripcion.text = "Descripción: $descripcion"
        tvPrecio.text = "Precio: $precio USD"

        btnSolicitar.setOnClickListener {
            val fecha = "2025-02-11" // Se puede mejorar para elegir fecha
            val horaInicio = "08:00 AM"
            val horaFin = "10:00 AM"

            val registrada = databaseHelper.insertarSolicitud(idEstacionamiento, fecha, horaInicio, horaFin)
            if (registrada) {
                Toast.makeText(this, "Solicitud registrada correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar solicitud", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
