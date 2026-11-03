package pe.edu.upeu.pharmamobil.evidencias

/**
 * Modelos de trazabilidad para documentar la práctica de sesión 13.
 * No representan una ejecución ni generan capturas automáticamente.
 * Copiar/integrar en el módulo compartido real cuando el proyecto KMP esté disponible.
 */
enum class EvidenceStatus { PENDING, PASSED, FAILED, BLOCKED }
enum class PermissionDecision { GRANTED, DENIED, PERMANENTLY_DENIED }
enum class Severity { HIGH, MEDIUM, LOW }

data class EvidenceRecord(
    val step: Int,
    val description: String,
    val platform: String,
    val capturedAtIso: String?,
    val screenshotPath: String?,
    val localState: String,
    val remoteState: String?,
    val status: EvidenceStatus
) {
    init { require(step in 1..10) { "step must be between 1 and 10" } }
    fun hasCompleteEvidence(): Boolean =
        status != EvidenceStatus.PENDING &&
            !capturedAtIso.isNullOrBlank() &&
            !screenshotPath.isNullOrBlank()
}

data class PermissionEvidence(
    val permission: String,
    val decision: PermissionDecision,
    val userMessage: String,
    val recoveryPath: String?,
    val screenshotPath: String?
)

data class IntegrationGap(
    val title: String,
    val severity: Severity,
    val decision: String,
    val targetDateIso: String,
    val justification: String
)

object Session13Steps {
    val descriptions = listOf(
        "Primera apertura sin sesión",
        "Inicio de sesión correcto",
        "Primera sincronización",
        "Registro online",
        "Registro sin conexión",
        "Sincronización tras reconexión",
        "Error de validación",
        "Operación sin permisos de rol",
        "Token vencido",
        "Cierre de sesión"
    )
}
