package com.example.ccgr12024b_wbrj

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AgregarAvionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_avion)

        val etNombre: EditText = findViewById(R.id.etNombre)
        val etModelo: EditText = findViewById(R.id.etModelo)
        val etFabricante: EditText = findViewById(R.id.etFabricante)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nuevoAvion = Avion(
                id = BaseDeDatos.aviones.size + 1,
                modelo = etModelo.text.toString(),
                fabricante = etFabricante.text.toString(),
                anio = 2023
            )

            BaseDeDatos.agregarAvion(nuevoAvion)
            finish()
        }
    }
}
