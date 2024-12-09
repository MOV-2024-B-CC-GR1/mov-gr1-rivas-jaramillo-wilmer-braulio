import java.util.Date

data class ParteAvion(
    val nombre: String,
    val numeroSerie: Int,
    var precio: Double,
    val fechaMantenimiento: Date,
    var esEsencial: Boolean
)
