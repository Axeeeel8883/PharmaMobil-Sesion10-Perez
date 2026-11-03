// EJEMPLO DE INTEGRACIÓN, NO INSTALADO EN UNA ACTIVITY DE ESTE REPOSITORIO.
// Android 13+ exige POST_NOTIFICATIONS para notificaciones no exentas.
// Adaptar a Activity real y revisar según el targetSdk del proyecto.
// AndroidManifest.xml: <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
//
// En una ComponentActivity:
// private val notificationLauncher =
//     registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
//         // Registrar resultado real (no persistir capturas falsas).
//     }
//
// fun requestNotifications() {
//     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//         when {
//             ContextCompat.checkSelfPermission(
//                 this, Manifest.permission.POST_NOTIFICATIONS
//             ) == PackageManager.PERMISSION_GRANTED -> { /* concedido */ }
//             ActivityCompat.shouldShowRequestPermissionRationale(
//                 this, Manifest.permission.POST_NOTIFICATIONS
//             ) -> { /* mostrar explicación y dejar decidir */ }
//             else -> notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//         }
//     }
// }
//
// NOTA: shouldShowRequestPermissionRationale=false no basta para inferir
// 'denegado definitivamente'; también ocurre antes de solicitar el permiso.
// La bitácora debe basarse en pruebas y estado real.
