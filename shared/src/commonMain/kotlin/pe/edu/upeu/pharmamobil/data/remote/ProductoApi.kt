package pe.edu.upeu.pharmamobil.data.remote
import pe.edu.upeu.pharmamobil.domain.model.Producto

/** Puerto del backend: debe implementarse con Ktor y JWT vigente. */
interface ProductoApi {
 suspend fun listar(): List<Producto>
 suspend fun registrar(producto: Producto): Producto
}
