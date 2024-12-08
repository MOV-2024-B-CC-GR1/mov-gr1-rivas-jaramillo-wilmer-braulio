import java.util.Date

fun main() {
    val filePath = "aviones.txt" // Ruta del archivo donde se guardarán los aviones
    val aviones = leerAvionesDesdeArchivo(filePath).toMutableList() // Leer aviones al inicio

    while (true) {
        println("\n--- Menú CRUD ---")
        println("1. Crear Avión")
        println("2. Leer Aviones")
        println("3. Actualizar Avión")
        println("4. Eliminar Avión")
        println("5. Manejar Partes de un Avión")
        println("6. Salir")
        print("Selecciona una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> crearAvion(aviones, filePath) // Crear un nuevo avión y guardar en el archivo
            2 -> leerAviones(aviones) // Mostrar todos los aviones en memoria
            3 -> actualizarAvion(aviones, filePath) // Actualizar un avión existente
            4 -> eliminarAvion(aviones, filePath) // Eliminar un avión y guardar cambios
            5 -> seleccionarAvionParaPartes(aviones, filePath) // Manejar partes de un avión
            6 -> {
                println("¡Saliendo del programa!")
                break
            }
            else -> println("Opción no válida.")
        }
    }
}

// Submenú para manejar las partes de un avión seleccionado
fun seleccionarAvionParaPartes(aviones: MutableList<Avion>, filePath: String) {
    println("Introduce el nombre del avión para manejar sus partes:")
    val nombreAvion = readLine() ?: ""
    val avion = aviones.find { it.nombre == nombreAvion }

    if (avion != null) {
        manejarPartes(avion, aviones, filePath)
    } else {
        println("Avión no encontrado.")
    }
}

fun manejarPartes(avion: Avion, aviones: MutableList<Avion>, filePath: String) {
    while (true) {
        println("\n--- Menú Partes del Avión: ${avion.nombre} ---")
        println("1. Crear Parte")
        println("2. Leer Partes")
        println("3. Actualizar Parte")
        println("4. Eliminar Parte")
        println("5. Regresar al Menú Principal")
        print("Selecciona una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> crearParte(avion, aviones, filePath) // Crear una nueva parte y guardar
            2 -> leerPartes(avion) // Leer partes del avión en memoria
            3 -> actualizarParte(avion, aviones, filePath) // Actualizar una parte existente
            4 -> eliminarParte(avion, aviones, filePath) // Eliminar una parte y guardar
            5 -> break
            else -> println("Opción no válida.")
        }
    }
}

