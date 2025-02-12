package com.example.ccgr12024b_wbrj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EstacionamientosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var estacionamientoAdapter: EstacionamientoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_estacionamientos)

        recyclerView = findViewById(R.id.recyclerEstacionamientos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaEstacionamientos = listOf(
            Estacionamiento(
                id = 1,
                nombre = "Parqueadero Central",
                ubicacion = "Av. Principal 123, Quito",
                descripcion = "Amplio espacio y seguridad 24/7.",
                precio = 1.50,
                latitud = -0.1807,
                longitud = -78.4678,
                imagen = R.drawable.estacionamiento1
            ),
            Estacionamiento(
                id = 2,
                nombre = "Estacionamiento Norte",
                ubicacion = "Calle Norte 456, Quito",
                descripcion = "Acceso 24 horas con vigilancia.",
                precio = 2.00,
                latitud = -0.1750,
                longitud = -78.4800,
                imagen = R.drawable.estacionamiento2
            ),
            Estacionamiento(
                id = 3,
                nombre = "Parqueadero Sur",
                ubicacion = "Av. Sur 789, Quito",
                descripcion = "Amplio parqueadero techado.",
                precio = 1.80,
                latitud = -0.2000,
                longitud = -78.4500,
                imagen = R.drawable.estacionamiento3
            )
        )

        estacionamientoAdapter = EstacionamientoAdapter(this, listaEstacionamientos, DatabaseHelper(this))
        recyclerView.adapter = estacionamientoAdapter
    }
}
