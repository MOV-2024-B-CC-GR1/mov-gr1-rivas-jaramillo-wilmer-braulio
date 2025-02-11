package com.example.ccgr12024b_wbrj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var btnVerMapa: Button  // Agregar variable para el botón

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        listView = findViewById(R.id.listViewAviones)
        val btnAgregar: Button = findViewById(R.id.btnAgregarAvion)
        val btnVerMapa: Button = findViewById(R.id.btnVerMapa)  // 🔹 Inicializa correctamente

        btnAgregar.setOnClickListener {
            startActivity(Intent(this, AgregarAvionActivity::class.java))
        }

        btnVerMapa.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            startActivity(intent)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val aviones = databaseHelper.obtenerAviones()
            val avion = aviones[position]

            val intent = Intent(this, DetalleAvionActivity::class.java)
            intent.putExtra("AVION_ID", avion.id)
            intent.putExtra("MODELO", avion.modelo)
            intent.putExtra("FABRICANTE", avion.fabricante)
            intent.putExtra("LATITUD", avion.latitud)
            intent.putExtra("LONGITUD", avion.longitud)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        actualizarLista()
    }

    private fun actualizarLista() {
        val aviones = databaseHelper.obtenerAviones()
        val nombresAviones = aviones.map { it.modelo }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresAviones)
        listView.adapter = adapter
    }
}