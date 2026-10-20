# Informe técnico – Práctica calificada N.º 11

**Estudiante:** Axel Perez  
**Curso:** Desarrollo de Aplicaciones Móviles  
**Fecha:** 20/10/2026  
**Tema:** Persistencia local con SQLDelight y estrategia offline-first  
**Repositorio:** https://github.com/Axeeeel8883/PharmaMobil-Sesion10-Perez/tree/feature/offline-first-perez

## 1. Objetivo
Preparar las capas de persistencia local y sincronización para PharmaMobil, manteniendo SQLDelight como futura fuente de verdad de la interfaz.

## 2. Situación del código fuente
La rama master disponible del profesor corresponde a una etapa anterior, con repositorio de productos en memoria. El repositorio del estudiante al comenzar contenía solo README.md, sin archivos de la sesión 10. Por tanto esta rama aporta **componentes de integración** y no una aplicación compilada completa.

## 3. Desarrollo registrado en GitHub
- Esquema SQLDelight `Producto.sq` con campos `id`, `nombre`, `precio`, `stock` y `pendiente`.
- Consultas `listarTodos`, `listarPendientes`, `insertar`, `borrarSincronizados`, `borrarPorId` y `siguienteIdLocal`.
- Interfaz `CreadorDeDriver` y clases Android/iOS para `pharma.db`.
- `ProductoLocal` expone un `Flow` y operaciones de escritura local.
- `ProductoRepositorioOffline` registra primero en local, luego intenta enviar pendientes y finalmente descargar desde servidor.
- Puerto `ProductoApi` y boceto `KtorProductoApi` para los endpoints de productos del backend.

## 4. Flujo offline-first propuesto
1. Registrar un producto en SQLite con identificador temporal negativo.
2. Observar los cambios locales desde `Flow`.
3. Mantener el producto pendiente si falla la red.
4. Con conectividad, subir primero los pendientes.
5. Solo después descargar del servidor y actualizar filas sincronizadas.

## 5. Diferencias comprobadas con PharmaSoft
`POST /api/v1/productos` requiere `nombre`, `precio`, `stock`, `estado` y `categoriaId`. El listado `GET /api/v1/productos` es paginado y las peticiones requieren autenticación. El cliente de integración debe manejar JWT, un `categoriaId` existente y el contrato JSON real del backend.

## 6. Pruebas previstas (NO EJECUTADAS)
- Sin conexión: creación local visible y fila pendiente.
- Con conexión: subida de producto y posterior descarga.
- Reconexión: producto persistido en PharmaSoft y ya no pendiente.
- Aplicación abierta con backend apagado en Android e iOS.
- Al menos tres pruebas de `commonTest`, ejecutadas y aprobadas.

## 7. Estado respecto a los 10 criterios
1. Esquema generado y compilado: **no verificado**.
2. Drivers registrados en Koin y en pruebas: **pendiente**.
3. Capa local aislada de Ktor/DTO: **implementación preparada, sin compilar**.
4. Repositorio completo `ProductoRepository` con `Flow`: **integración pendiente**.
5. ViewModel suscrito a `Flow`: **pendiente**.
6. Aplicación ejecutada sin servidor: **sin evidencia**.
7. Pendientes locales: **lógica preparada, sin ejecutar**.
8. Subir antes de bajar: **implementado en componente preliminar**.
9. Producto visible en Swagger: **sin evidencia**.
10. Tres pruebas en `commonTest`: **pendiente**.

## 8. Limitaciones y siguientes pasos
El código en GitHub **no debe calificarse como app completa** hasta incorporar los módulos de la sesión 10, ajustar Gradle/Koin/DTO, compilar, ejecutar pruebas, obtener capturas reales y validar la conexión con PharmaSoft. Se debe considerar idempotencia de POST para evitar duplicaciones si se interrumpe la sincronización.

## 9. Evidencias a anexar en la entrega final
Captura de Android offline, captura de iOS offline, producto creado en modo avión, reconexión, producto con ID real en Swagger, salida de ejecución de tres pruebas y commits de la rama.

## 10. Fuentes de desarrollo
Guía Práctica de Laboratorio N.º 11 (2026), repositorios oficiales `dreyna/pharmaMobil` y `dreyna/pharmaSoft`.
