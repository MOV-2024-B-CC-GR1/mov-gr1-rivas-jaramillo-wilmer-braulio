package com.example.ccgr12024b_wbrj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewAviones)
        val btnAgregar: Button = findViewById(R.id.btnAgregarAvion)

        actualizarLista()

        btnAgregar.setOnClickListener {
            startActivity(Intent(this, AgregarAvionActivity::class.java))
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            BaseDeDatos.eliminarAvion(BaseDeDatos.aviones[position].id)
            actualizarLista()
        }
    }

    private fun actualizarLista() {
        val nombresAviones = BaseDeDatos.obtenerAviones().map { it.modelo }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresAviones)
        listView.adapter = adapter
    }
}

