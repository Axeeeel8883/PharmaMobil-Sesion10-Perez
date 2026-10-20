package pe.edu.upeu.pharmamobil.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import pe.edu.upeu.pharmamobil.domain.model.Producto

@Serializable
data class ProductoDto(val id:Long,val nombre:String,val precio:Double,val stock:Int)
@Serializable
data class ProductoPaginaDto(
 val contenido:List<ProductoDto> = emptyList(),
 val pagina:Int=0,
 val tamanio:Int=20,
 val totalElementos:Long=0,
 val totalPaginas:Int=0,
 val ultima:Boolean=true
)
@Serializable
data class ProductoNuevoDto(
 val nombre:String,val precio:Double,val stock:Int,
 val estado:Boolean=true,val categoriaId:Long=1
)
/** El backend usa una pagina; ajustar el nombre de la lista segun PaginaResponseDTO. */
class KtorProductoApi(
 private val client:HttpClient,
 private val baseUrl:String,
 private val token:()->String,
 private val categoriaId:()->Long
): ProductoApi {
 override suspend fun registrar(producto:Producto):Producto {
  val result:ProductoDto=client.post("$baseUrl/api/v1/productos") {
    bearerAuth(token())
    contentType(ContentType.Application.Json)
    setBody(ProductoNuevoDto(producto.nombre,producto.precio,producto.stock,true,categoriaId()))
  }.body()
  return Producto(result.id,result.nombre,result.precio,result.stock)
 }
 override suspend fun listar():List<Producto> {
   val resultados=mutableListOf<Producto>()
   var pagina=0
   while(true) {
      val page:ProductoPaginaDto=client.get("$baseUrl/api/v1/productos") {
        bearerAuth(token())
        url { parameters.append("pagina",pagina.toString());parameters.append("tamanio","100") }
      }.body()
      resultados.addAll(page.contenido.map { Producto(it.id,it.nombre,it.precio,it.stock) })
      if(page.ultima) break
      pagina++
   }
   return resultados
 }
}
