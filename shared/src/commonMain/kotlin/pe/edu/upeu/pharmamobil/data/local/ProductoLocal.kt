package pe.edu.upeu.pharmamobil.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pe.edu.upeu.pharmamobil.db.PharmaDb
import pe.edu.upeu.pharmamobil.domain.model.Producto

class ProductoLocal(private val db: PharmaDb) {
 private val q = db.productoQueries
 fun observar(): Flow<List<Producto>> =
   q.listarTodos().asFlow().mapToList(Dispatchers.Default)
    .map { rows -> rows.map { Producto(it.id,it.nombre,it.precio,it.stock.toInt()) } }
 fun pendientes(): List<Producto> =
   q.listarPendientes().executeAsList()
    .map { Producto(it.id,it.nombre,it.precio,it.stock.toInt()) }
 fun guardarPendiente(p: Producto): Producto {
   val id = minOf(q.siguienteIdLocal().executeAsOne(),0L)-1L
   q.insertar(id,p.nombre,p.precio,p.stock.toLong(),1L)
   return p.copy(id=id)
 }
 fun quitarPendiente(id: Long) { q.borrarPorId(id) }
 fun reemplazarSincronizados(productos: List<Producto>) {
   q.transaction {
     q.borrarSincronizados()
     productos.forEach { q.insertar(it.id,it.nombre,it.precio,it.stock.toLong(),0L) }
   }
 }
}
