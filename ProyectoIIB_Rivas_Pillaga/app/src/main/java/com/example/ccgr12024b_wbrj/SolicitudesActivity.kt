package com.example.ccgr12024b_wbrj

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SolicitudesActivity : AppCompatActivity() {

    private lateinit var recyclerSolicitudes: RecyclerView
    private lateinit var adapter: SolicitudAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_solicitudes)

        recyclerSolicitudes = findViewById(R.id.recyclerSolicitudes)

        // 📌 Lista manual de solicitudes (mismo formato que estacionamientos)
        val listaSolicitudes = listOf(
            Solicitud(
                imagen = R.drawable.estacionamiento_logo,
                nombreEstacionamiento = "Estacionamiento Norte",
                ubicacion = "Calle Norte 456, Quito",
                precio = "$2.00/hora",
                fecha = "12/02/2025",
                horaInicio = "08:00 AM",
                horaFin = "10:00 AM"
            ),
            Solicitud(
                imagen = R.drawable.estacionamiento_logo,
                nombreEstacionamiento = "Parqueadero Sur",
                ubicacion = "Av. Sur 789, Quito",
                precio = "$1.80/hora",
                fecha = "12/02/2025",
                horaInicio = "09:30 AM",
                horaFin = "11:30 AM"
            )
        )

        // 🔹 Mostrar mensaje con la cantidad de solicitudes
        Toast.makeText(this, "Total solicitudes: ${listaSolicitudes.size}", Toast.LENGTH_LONG).show()

        // 📌 Asignar el adaptador con los datos manuales
        adapter = SolicitudAdapter(this, listaSolicitudes)
        recyclerSolicitudes.layoutManager = LinearLayoutManager(this)
        recyclerSolicitudes.adapter = adapter
    }
}