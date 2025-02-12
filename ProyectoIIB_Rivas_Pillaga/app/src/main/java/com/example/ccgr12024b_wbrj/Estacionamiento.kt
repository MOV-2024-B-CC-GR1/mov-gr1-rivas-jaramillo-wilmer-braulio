package com.example.ccgr12024b_wbrj

data class Estacionamiento(
    val id: Int,
    val nombre: String,
    val ubicacion: String,
    val descripcion: String,
    val precio: Double,
    val latitud: Double,
    val longitud: Double,
    val imagen: Int // Agregamos el parámetro de la imagen
)