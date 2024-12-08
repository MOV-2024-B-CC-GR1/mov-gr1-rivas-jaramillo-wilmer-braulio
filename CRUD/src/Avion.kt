import java.util.Date

data class Avion(
    val nombre: String,
    var numeroPartes: Int,
    val fechaFabricacion: Date,
    var pesoMaximo: Double,
    var enServicio: Boolean,
    val partes: MutableList<ParteAvion> = mutableListOf()
)

data class ParteAvion(
    val nombre: String,
    val numeroSerie: Int,
    var precio: Double,
    val fechaMantenimiento: Date,
    var esEsencial: Boolean
)
