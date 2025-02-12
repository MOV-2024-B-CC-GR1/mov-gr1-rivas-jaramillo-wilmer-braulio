package com.example.ccgr12024b_wbrj

data class Solicitud(
    val imagen: Int,
    val nombreEstacionamiento: String,
    val ubicacion: String,
    val precio: String, // Debe ser String
    val fecha: String,
    val horaInicio: String,
    val horaFin: String
)


