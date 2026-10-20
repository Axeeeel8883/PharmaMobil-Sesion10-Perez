# Integración sesión 11 — PharmaMobil

**Estado:** código de base preparado; integración con la sesión 10, compilación y evidencia pendientes. No presentar como una práctica verificada.

## Qué incluye este repositorio
* SQLDelight Producto.sq, campos y consultas para distinguir pendientes.
* Drivers por plataforma, clase ProductoLocal y repositorio offline-first.
* Puerto ProductoApi e implementación HTTP inicial con Ktor.

## Pasos restantes obligatorios
1. Recuperar el proyecto PharmaMobil completo de la sesión 10. Este repositorio de inicio solo tenía README.md; no contiene las clases de dominio y ViewModel.
2. Agregar plugin SQLDelight 2.1.0 a libs.versions.toml y shared/build.gradle.kts, y dependencias runtime, coroutines-extensions, android-driver, native-driver.
3. Agregar las dependencias Ktor y kotlinx.serialization; configurar HttpClient con JSON y motores Android/iOS.
4. Configurar sqldelight { databases { create("PharmaDb") { packageName.set("pe.edu.upeu.pharmamobil.db") } } }.
5. Registrar CreadorDeDriverAndroid / Ios en platformModule y PharmaDb en el dataModule de Koin.
6. Adaptar ProductoRepository, ProductoViewModel y ProductoScreen para observar Flow y ofrecer el boton Sincronizar; asegurar funcionamiento local sin backend.
7. Ajustar ProductoPaginaDto a los campos JSON exactos de PaginaResponseDTO; obtener JWT desde el login, proporcionar categoriaId valido.
8. Añadir por lo menos tres pruebas en commonTest (sin red, orden subir-bajar, reconexion).
9. Ejecutar Gradle, probar el modo avión y verificar en Swagger un producto con ID positivo.
10. Tomar capturas reales en Android y iOS y documentar resultados.

## Riesgos técnicos
* Si el POST llegó al servidor y la app se interrumpe antes de borrar el pendiente, se podría reenviar: hace falta estrategia de idempotencia para garantizar ausencia de duplicados.
* La descarga paginada debe verificarse con DTO real de PharmaSoft antes de confiar en este ejemplo.
* Esta rama no es una app ejecutable por sí sola hasta incorporar la sesión 10.
