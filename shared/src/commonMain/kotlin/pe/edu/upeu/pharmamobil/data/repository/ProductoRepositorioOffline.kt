package pe.edu.upeu.pharmamobil.data.repository

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import pe.edu.upeu.pharmamobil.data.local.ProductoLocal
import pe.edu.upeu.pharmamobil.data.remote.ProductoApi
import pe.edu.upeu.pharmamobil.domain.model.Producto

/** Persistencia local como fuente unica de verdad. */
class ProductoRepositorioOffline(
 private val local: ProductoLocal,
 private val remoto: ProductoApi
) {
 fun observar(): Flow<List<Producto>> = local.observar()

 suspend fun registrar(producto: Producto): Producto {
   val guardado=local.guardarPendiente(producto)
   try { sincronizar() }
   catch (e: CancellationException) { throw e }
   catch (_: Exception) { /* se conserva la fila pendiente */ }
   return guardado
 }

 suspend fun sincronizar() {
   // La subida SIEMPRE sucede antes de la descarga.
   local.pendientes().forEach { pendiente ->
     remoto.registrar(pendiente)
     // No eliminar la fila hasta que la API confirme el POST.
     local.quitarPendiente(pendiente.id)
   }
   local.reemplazarSincronizados(remoto.listar())
 }
}
