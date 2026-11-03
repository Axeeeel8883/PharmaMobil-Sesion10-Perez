package pe.edu.upeu.pharmamobil.integrador

/**
 * Motor de sincronización independiente de Ktor y SQLDelight.
 * Integrar sus puertos con los repositorios REALES antes de declararlo funcional.
 */
data class FilaPendiente(
    val idLocal: Long,
    val intentos: Int = 0,
    val ultimoError: String? = null
)
sealed interface ResultadoEnvio {
    data class Exito(val idServidor: Long) : ResultadoEnvio
    data class Transitorio(val motivo: String) : ResultadoEnvio
    data class Permanente(val motivo: String) : ResultadoEnvio
}
data class ResumenSync(val enviadas: Int, val fallidas: Int, val pendientes: Int)

interface ColaLocal {
    suspend fun pendientes(maxIntentos: Int): List<FilaPendiente>
    suspend fun confirmar(idLocal: Long, idServidor: Long)
    suspend fun sumarIntento(idLocal: Long, error: String)
    suspend fun marcarFallida(idLocal: Long, error: String)
    suspend fun contarPendientes(maxIntentos: Int): Int
    suspend fun conciliarRemotos()
}
interface EnviadorRemoto {
    suspend fun enviar(fila: FilaPendiente): ResultadoEnvio
}
interface Esperador { suspend fun esperar(ms: Long) }

class Sincronizador(
    private val local: ColaLocal,
    private val remoto: EnviadorRemoto,
    private val esperador: Esperador,
    private val maxIntentos: Int = 5
) {
    init { require(maxIntentos > 0) }
    suspend fun ejecutar(): ResumenSync {
        var enviadas = 0
        var fallidas = 0
        var hayFalloTransitorio = false
        for (fila in local.pendientes(maxIntentos)) {
            when (val resultado = remoto.enviar(fila)) {
                is ResultadoEnvio.Exito -> {
                    local.confirmar(fila.idLocal, resultado.idServidor)
                    enviadas++
                }
                is ResultadoEnvio.Permanente -> {
                    // Es importante que marcarFallida excluya esta fila de futuros reintentos.
                    local.marcarFallida(fila.idLocal, resultado.motivo)
                    fallidas++
                }
                is ResultadoEnvio.Transitorio -> {
                    local.sumarIntento(fila.idLocal, resultado.motivo)
                    hayFalloTransitorio = true
                    if (fila.intentos + 1 < maxIntentos) {
                        esperador.esperar(esperaMs(fila.intentos))
                    } else {
                        fallidas++
                    }
                    // La red podría seguir caída: no enviar más operaciones en este ciclo.
                    break
                }
            }
        }
        // Una descarga solo se realiza después de las subidas, y con servicio utilizable.
        if (!hayFalloTransitorio) local.conciliarRemotos()
        return ResumenSync(enviadas, fallidas, local.contarPendientes(maxIntentos))
    }
    companion object {
        fun esperaMs(intentosPrevios: Int): Long =
            1_000L shl intentosPrevios.coerceIn(0, 4)
    }
}
