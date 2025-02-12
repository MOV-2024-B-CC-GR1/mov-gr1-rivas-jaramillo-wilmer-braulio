package com.example.ccgr12024b_wbrj

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SolicitudAdapter(private val context: Context, private val listaSolicitudes: List<Solicitud>) :
    RecyclerView.Adapter<SolicitudAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgEstacionamiento: ImageView = view.findViewById(R.id.imgEstacionamiento)
        val tvNombreEstacionamiento: TextView = view.findViewById(R.id.tvNombreEstacionamiento)
        val tvUbicacion: TextView = view.findViewById(R.id.tvUbicacion)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_solicitud, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val solicitud = listaSolicitudes[position]
        holder.imgEstacionamiento.setImageResource(solicitud.imagen)
        holder.tvNombreEstacionamiento.text = solicitud.nombreEstacionamiento
        holder.tvUbicacion.text = solicitud.ubicacion
        holder.tvPrecio.text = solicitud.precio
        holder.tvEstado.text = "Estado: Pendiente"
    }

    override fun getItemCount(): Int {
        return listaSolicitudes.size
    }
}
