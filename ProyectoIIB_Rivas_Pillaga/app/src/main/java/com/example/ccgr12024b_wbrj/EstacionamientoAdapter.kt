package com.example.ccgr12024b_wbrj

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EstacionamientoAdapter(
    private val context: Context,
    private val listaEstacionamientos: List<Estacionamiento>,
    private val databaseHelper: DatabaseHelper
) : RecyclerView.Adapter<EstacionamientoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgEstacionamiento: ImageView = view.findViewById(R.id.imgEstacionamiento)
        val tvNombreEstacionamiento: TextView = view.findViewById(R.id.tvNombreEstacionamiento)
        val tvUbicacion: TextView = view.findViewById(R.id.tvUbicacion)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
        val btnVerMapa: Button = view.findViewById(R.id.btnVerMapa)
        val btnSolicitarParqueadero: Button = view.findViewById(R.id.btnSolicitarParqueadero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_estacionamiento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estacionamiento = listaEstacionamientos[position]

        holder.imgEstacionamiento.setImageResource(estacionamiento.imagen)
        holder.tvNombreEstacionamiento.text = estacionamiento.nombre
        holder.tvUbicacion.text = estacionamiento.ubicacion
        holder.tvPrecio.text = "$${estacionamiento.precio}/hora"

        // 🔹 Evento para abrir el mapa
        holder.btnVerMapa.setOnClickListener {
            val intent = Intent(context, MapaActivity::class.java).apply {
                putExtra("LATITUD", estacionamiento.latitud)
                putExtra("LONGITUD", estacionamiento.longitud)
                putExtra("NOMBRE", estacionamiento.nombre)
            }
            context.startActivity(intent)
        }

        // 🔹 Evento para "Solicitar" (solo muestra un mensaje por ahora)
        holder.btnSolicitarParqueadero.setOnClickListener {
            // Aquí puedes agregar la lógica para insertar en la BD
            Toast.makeText(context, "Solicitud enviada para ${estacionamiento.nombre}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = listaEstacionamientos.size
}
