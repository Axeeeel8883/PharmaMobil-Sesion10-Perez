# Revisión comparativa de repositorios

Referencia: https://github.com/DJuanParedes/pharmaMobil (master).

El repositorio de referencia contiene las carpetas `androidApp`, `shared` e `iosApp`, pero la versión pública examinada no trae una solución SQLDelight offline-first, JWT ni el CRUD REST de sesión 10. Su estructura sirve como ejemplo inicial de Kotlin Multiplatform, pero copiarla no cumpliría la guía.

En la rama `feature/offline-first-perez` se prepararon `Producto.sq`, `ProductoLocal`, drivers Android/iOS, interfaz `ProductoApi`, cliente Ktor y repositorio con sincronización primero subida y luego bajada. Se corrigió el modelo de paginación de Ktor para usar `ultima` del backend real de PharmaSoft.

**Limitación:** el repositorio del alumno aún carece de los módulos completos Gradle y de las sesiones previas; la aplicación no ha sido compilada ni ejecutada. La implementación, Koin, JWT, ViewModel y tres pruebas verificadas siguen pendientes. No se han producido capturas de Android/iOS/Swagger.
