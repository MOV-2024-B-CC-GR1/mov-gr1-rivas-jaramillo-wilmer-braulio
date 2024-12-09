import java.io.File
import java.util.*

fun crearAvion(aviones: MutableList<Avion>, filePath: String): Avion {
    println("Introduce el nombre del avión:")
    val nombre = readLine() ?: ""
    println("Introduce el número de partes:")
    val numeroPartes = readLine()?.toIntOrNull() ?: 0
    println("Introduce el peso máximo:")
    val pesoMaximo = readLine()?.toDoubleOrNull() ?: 0.0
    println("¿Está en servicio? (true/false):")
    val enServicio = readLine()?.toBoolean() ?: false

    val avion = Avion(
        nombre = nombre,
        numeroPartes = numeroPartes,
        fechaFabricacion = Date(),
        pesoMaximo = pesoMaximo,
        enServicio = enServicio
    )

    // Agregar el avión a la lista y guardarlo en el archivo
    aviones.add(avion)
    guardarAvionesEnArchivo(aviones, filePath)

    println("¡Avión creado y guardado exitosamente!")
    return avion
}


fun leerAviones(aviones: List<Avion>) {
    if (aviones.isEmpty()) {
        println("No hay aviones registrados.")
    } else {
        aviones.forEach { println(it) }
    }
}

fun actualizarAvion(aviones: MutableList<Avion>, filePath: String) {
    println("Introduce el nombre del avión a actualizar:")
    val nombreAvion = readLine() ?: ""
    val avion = aviones.find { it.nombre == nombreAvion }

    if (avion != null) {
        println("Introduce el nuevo peso máximo:")
        avion.pesoMaximo = readLine()?.toDoubleOrNull() ?: avion.pesoMaximo

        println("¿Está en servicio? (true/false):")
        avion.enServicio = readLine()?.toBoolean() ?: avion.enServicio

        guardarAvionesEnArchivo(aviones, filePath) // Guardar cambios en el archivo
        println("¡Avión actualizado exitosamente!")
    } else {
        println("Avión no encontrado.")
    }
}

fun eliminarAvion(aviones: MutableList<Avion>, filePath: String) {
    println("Introduce el nombre del avión a eliminar:")
    val nombreAvion = readLine() ?: ""
    val avionEliminado = aviones.removeIf { it.nombre == nombreAvion }

    if (avionEliminado) {
        guardarAvionesEnArchivo(aviones, filePath) // Guardar cambios en el archivo
        println("¡Avión eliminado exitosamente!")
    } else {
        println("Avión no encontrado.")
    }
}

fun crearParte(avion: Avion, aviones: MutableList<Avion>, filePath: String) {
    println("Introduce el nombre de la parte:")
    val nombre = readLine() ?: ""
    println("Introduce el número de serie:")
    val numeroSerie = readLine()?.toIntOrNull() ?: 0
    println("Introduce el precio:")
    val precio = readLine()?.toDoubleOrNull() ?: 0.0
    println("¿Es esencial? (true/false):")
    val esEsencial = readLine()?.toBoolean() ?: false

    val nuevaParte = ParteAvion(
        nombre = nombre,
        numeroSerie = numeroSerie,
        precio = precio,
        fechaMantenimiento = Date(),
        esEsencial = esEsencial
    )

    avion.partes.add(nuevaParte)
    avion.numeroPartes = avion.partes.size

    // Guardar todos los aviones en el archivo
    guardarAvionesEnArchivo(aviones, filePath)

    println("¡Parte creada y guardada exitosamente!")
}

fun leerPartes(avion: Avion) {
    if (avion.partes.isEmpty()) {
        println("No hay partes registradas para este avión.")
    } else {
        avion.partes.forEach { println(it) }
    }
}

fun actualizarParte(avion: Avion, aviones: MutableList<Avion>, filePath: String) {
    println("Introduce el nombre de la parte a actualizar:")
    val nombreParte = readLine() ?: ""
    val parte = avion.partes.find { it.nombre == nombreParte }

    if (parte != null) {
        println("Introduce el nuevo precio:")
        parte.precio = readLine()?.toDoubleOrNull() ?: parte.precio

        println("¿Es esencial? (true/false):")
        parte.esEsencial = readLine()?.toBoolean() ?: parte.esEsencial

        guardarAvionesEnArchivo(aviones, filePath) // Guardar cambios en el archivo
        println("¡Parte actualizada exitosamente!")
    } else {
        println("Parte no encontrada.")
    }
}

fun eliminarParte(avion: Avion, aviones: MutableList<Avion>, filePath: String) {
    println("Introduce el nombre de la parte a eliminar:")
    val nombreParte = readLine() ?: ""
    val parteEliminada = avion.partes.removeIf { it.nombre == nombreParte }

    if (parteEliminada) {
        guardarAvionesEnArchivo(aviones, filePath) // Guardar cambios en el archivo
        println("¡Parte eliminada exitosamente!")
    } else {
        println("Parte no encontrada.")
    }
}
