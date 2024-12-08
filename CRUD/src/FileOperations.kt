import java.io.File
import java.util.*

fun guardarAvionesEnArchivo(aviones: List<Avion>, filePath: String) {
    val file = File(filePath)
    val data = aviones.joinToString("\n") { avion ->
        val partes = avion.partes.joinToString("|") { parte ->
            "${parte.nombre},${parte.numeroSerie},${parte.precio},${parte.fechaMantenimiento.time},${parte.esEsencial}"
        }
        "${avion.nombre},${avion.numeroPartes},${avion.fechaFabricacion.time},${avion.pesoMaximo},${avion.enServicio},$partes"
    }
    file.writeText(data)
}

fun leerAvionesDesdeArchivo(filePath: String): List<Avion> {
    val file = File(filePath)
    if (!file.exists()) return emptyList()

    return file.readLines().map { line ->
        val parts = line.split(",")
        val nombre = parts[0]
        val numeroPartes = parts[1].toInt()
        val fechaFabricacion = Date(parts[2].toLong())
        val pesoMaximo = parts[3].toDouble()
        val enServicio = parts[4].toBoolean()

        val partes = if (parts.size > 5) {
            line.substringAfterLast(",").split("|").mapNotNull { parteStr ->
                val parteData = parteStr.split(",")
                if (parteData.size == 5) {
                    ParteAvion(
                        nombre = parteData[0],
                        numeroSerie = parteData[1].toInt(),
                        precio = parteData[2].toDouble(),
                        fechaMantenimiento = Date(parteData[3].toLong()),
                        esEsencial = parteData[4].toBoolean()
                    )
                } else null
            }.toMutableList()
        } else {
            mutableListOf()
        }

        Avion(nombre, numeroPartes, fechaFabricacion, pesoMaximo, enServicio, partes)
    }
}




