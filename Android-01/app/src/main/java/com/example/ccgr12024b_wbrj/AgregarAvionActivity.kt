package com.example.ccgr12024b_wbrj

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class AgregarAvionActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_avion)

        databaseHelper = DatabaseHelper(this)

        val etModelo: EditText = findViewById(R.id.etModelo)
        val etFabricante: EditText = findViewById(R.id.etFabricante)
        val etLatitud: EditText = findViewById(R.id.etLatitud)
        val etLongitud: EditText = findViewById(R.id.etLongitud)

        val btnGuardar: Button = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val modelo = etModelo.text.toString()
            val fabricante = etFabricante.text.toString()
            val latitud = etLatitud.text.toString().toDoubleOrNull() ?: 0.0
            val longitud = etLongitud.text.toString().toDoubleOrNull() ?: 0.0

            if (modelo.isNotEmpty() && fabricante.isNotEmpty()) {
                val exito = databaseHelper.insertarAvion(modelo, fabricante, latitud, longitud)
                if (exito) {
                    Toast.makeText(this, "Avión guardado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
