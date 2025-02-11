package com.example.ccgr12024b_wbrj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleAvionActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private var avionId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_avion)

        databaseHelper = DatabaseHelper(this)

        val tvModelo: TextView = findViewById(R.id.tvModelo)
        val tvFabricante: TextView = findViewById(R.id.tvFabricante)
        val tvLatitud: TextView = findViewById(R.id.tvLatitud)
        val tvLongitud: TextView = findViewById(R.id.tvLongitud)
        val btnEliminar: Button = findViewById(R.id.btnEliminar)
        val btnModificar: Button = findViewById(R.id.btnModificar)
        val btnVerMapa: Button = findViewById(R.id.btnVerMapa)

        // Recibir datos del Intent
        avionId = intent.getIntExtra("AVION_ID", -1)
        val modelo = intent.getStringExtra("MODELO") ?: "No disponible"
        val fabricante = intent.getStringExtra("FABRICANTE") ?: "No disponible"
        val latitud = intent.getDoubleExtra("LATITUD", 0.0)
        val longitud = intent.getDoubleExtra("LONGITUD", 0.0)

        // Mostrar los datos en la interfaz
        tvModelo.text = "Modelo: $modelo"
        tvFabricante.text = "Fabricante: $fabricante"
        tvLatitud.text = "Latitud: $latitud"
        tvLongitud.text = "Longitud: $longitud"


        // Botón eliminar
        btnEliminar.setOnClickListener {
            databaseHelper.eliminarAvion(avionId)
            finish() // Cierra la actividad y vuelve a la lista
        }

        // Botón modificar (puede abrir una nueva actividad para editar)
        btnModificar.setOnClickListener {
            val intent = Intent(this, AgregarAvionActivity::class.java)
            intent.putExtra("AVION_ID", avionId)
            startActivity(intent)
        }

        // Botón ver mapa
        btnVerMapa.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            intent.putExtra("LATITUD", latitud)
            intent.putExtra("LONGITUD", longitud)
            startActivity(intent)
        }
    }
}
