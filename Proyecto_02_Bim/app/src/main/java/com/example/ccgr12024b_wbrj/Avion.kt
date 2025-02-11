package com.example.ccgr12024b_wbrj

// Entidad Avion
data class Avion(
    val id: Int,
    val modelo: String,
    val fabricante: String,
    val latitud: Double,    // Nuevo campo
    val longitud: Double    // Nuevo campo
)

// Entidad ParteAvion
data class ParteAvion(
    val id: Int,
    val avionId: Int,
    val nombreParte: String
)

// Simulación de base de datos
object BaseDeDatos {
    val aviones = mutableListOf<Avion>()
    val partesAvion = mutableListOf<ParteAvion>()

    fun agregarAvion(avion: Avion) {
        aviones.add(avion)
    }

    fun obtenerAviones(): List<Avion> {
        return aviones
    }

    fun eliminarAvion(id: Int) {
        aviones.removeIf { it.id == id }
        partesAvion.removeIf { it.avionId == id }
    }

    fun agregarParte(parte: ParteAvion) {
        partesAvion.add(parte)
    }

    fun obtenerPartes(avionId: Int): List<ParteAvion> {
        return partesAvion.filter { it.avionId == avionId }
    }
}