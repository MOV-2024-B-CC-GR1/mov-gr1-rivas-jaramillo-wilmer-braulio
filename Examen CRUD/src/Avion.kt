import java.util.Date

data class Avion(
    val nombre: String,
    var numeroPartes: Int,
    val fechaFabricacion: Date,
    var pesoMaximo: Double,
    var enServicio: Boolean,
    val partes: MutableList<ParteAvion> = mutableListOf()
)
