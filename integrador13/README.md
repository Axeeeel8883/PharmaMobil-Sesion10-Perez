# Sesión 13 — kit de integración

**ESTADO: código fuente de apoyo, NO una aplicación Android/iOS ejecutable verificada.**

La guía exige partir de un proyecto KMP funcional de Unidad 2, pero la rama previa de este repositorio era parcial. Por ese motivo los archivos de este directorio están aislados para no simular una integración que no existe.

## Contenido
- Modelo y motor `Sincronizador`: éxito, error transitorio, error permanente, límite de intentos y espera exponencial.
- Puertos `GestorPermisos` y `Telemetria` del dominio.
- `EstadoSincronizacion` para ViewModel.
- SQLDelight: migración y consultas de ejemplo.
- Documentación y plan de evidencias en `docs/SESION13-CHECKLIST.md`.

## Pendiente para completar los 7 pasos
1. Incorporar aplicación completa del proyecto docente `dreyna/pharmaMobil` en una rama compatible con `develop`, sin borrar cambios ajenos.
2. Adaptar entidad y consultas a base de datos real, crear driver y migración SQLDelight verificada.
3. Conectar `Sincronizador` a API Ktor y persistencia; añadir idempotencia a POST.
4. Mostrar `EstadoSincronizacion` mediante Flow observable en Compose.
5. Crear `GestorPermisosAndroid` con ActivityResultContracts y manifiesto; probar tres estados.
6. Configurar un proyecto Firebase real y registrar Android, sin subir credenciales privadas ni secretos.
7. Añadir tests con fakes/MockEngine; compilar en Android Studio, capturar pantallas reales y verificar iOS cuando esté disponible.

No se han ejecutado compilaciones ni tests del proyecto Android/iOS en este entorno.
