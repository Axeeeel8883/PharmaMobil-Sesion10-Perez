# Sesión 13 | Protocolo de evidencias — PharmaMobil

Alumno: Axel Pérez · Fecha de preparación: 2026-11-03

## Estado auditado
El repositorio propio contiene materiales parciales de la sesión 11. No se ha confirmado una compilación Android, una compilación iOS, autenticación, un servicio PharmaSoft operativo ni ejecución del recorrido. **No convertir pasos pendientes en resultados logrados.**

## Producto A: flujo de diez pasos
Para cada paso adjuntar una captura real, fecha/hora, dispositivo/emulador, estado local, estado remoto y resultado observado:
1. Primera apertura sin sesión: base local y token.
2. Login correcto: token protegido, rol, privilegios (ocultar el valor JWT).
3. Listado después de primera sincronización: recuento y fecha/hora.
4. Creación online: identificador de servidor.
5. Creación en modo avión: ID local provisional y pendiente.
6. Reconexión: pendientes antes/después e ID remoto.
7. Validación fallida: código HTTP y mensaje visible.
8. Operación sin rol: código HTTP (usualmente 403 si así responde backend) y UX.
9. Token vencido: código observado, sesión invalidada y destino.
10. Cierre de sesión: token borrado y destino de datos locales.

## Producto B: comparación
Android obligatorio. iOS solo cuando compile. Comparar tokens, permisos, modo avión, notificaciones, tipografías y zonas seguras. Para iOS no ejecutado, registrar error real y plan.

## Producto C: permisos
Cada permiso utilizado debe evidenciar: concedido, denegado y denegado definitivamente; anotar ruta a Ajustes. Si la app no pide ninguno, implementar permiso de notificaciones para Android 13+ antes de evaluar. El helper de esta rama NO sustituye la integración en Activity/Compose ni las capturas de dispositivo.

## Producto D: huecos sugeridos, pendientes de contrastar
- Alta: falta recuperar el proyecto KMP completo y su Gradle.
- Alta: falta integrar login/token/roles con backend.
- Alta: falta ejecutar y verificar sincronización online/offline.
- Media: falta verificar permiso de notificaciones Android 13+.
- Media: iOS no compilado ni verificado en este repositorio.

## Comandos a ejecutar cuando se agregue el proyecto completo
```bash
./gradlew tasks
./gradlew :composeApp:assembleDebug
./gradlew allTests
```
Ajustar nombres de módulos/tareas a los reales. Guardar los logs; no se han ejecutado aquí.

## Repositorio
Rama: https://github.com/Axeeeel8883/PharmaMobil-Sesion10-Perez/tree/feature/integrador-13
Fuente docente: https://github.com/dreyna/pharmaMobil
