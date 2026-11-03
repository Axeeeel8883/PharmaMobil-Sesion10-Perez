package pe.edu.upeu.pharmamobil.integrador

enum class Permiso { CAMARA, UBICACION, NOTIFICACIONES }
sealed interface EstadoPermiso {
    data object Concedido : EstadoPermiso
    data object Denegado : EstadoPermiso
    data object DenegadoParaSiempre : EstadoPermiso
}
interface GestorPermisos {
    suspend fun estado(permiso: Permiso): EstadoPermiso
    suspend fun solicitar(permiso: Permiso): EstadoPermiso
    fun abrirAjustes()
}
interface Telemetria {
    fun registrarError(error: Throwable, contexto: Map<String, String> = emptyMap())
    fun registrarEvento(nombre: String, datos: Map<String, String> = emptyMap())
}
/** Implementación local para tests; no envía información a Firebase. */
class TelemetriaEnMemoria : Telemetria {
    val eventos = mutableListOf<String>()
    override fun registrarError(error: Throwable, contexto: Map<String, String>) {
        eventos += "error:" + error::class.simpleName
    }
    override fun registrarEvento(nombre: String, datos: Map<String, String>) {
        eventos += nombre
    }
}
data class EstadoSincronizacion(
    val enCurso: Boolean = false,
    val pendientes: Int = 0,
    val fallidas: Int = 0,
    val ultimaSyncEpochMs: Long? = null
)
