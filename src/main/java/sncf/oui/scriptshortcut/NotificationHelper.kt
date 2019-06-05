package sncf.oui.scriptshortcut

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType

object NotificationHelper {

    private val INFO = NotificationGroup.logOnlyGroup("Script Shortcut (Logging)")
    private val ERRORS = NotificationGroup.balloonGroup("Script Shortcut (Errors)")

    fun info(message: String) {
        sendNotification(
            message,
            NotificationType.INFORMATION,
            INFO
        )
    }

    fun error(message: String) {
        sendNotification(
            message,
            NotificationType.ERROR,
            ERRORS
        )
    }

    private fun sendNotification(
        message: String,
        notificationType: NotificationType,
        notificationGroup: NotificationGroup
    ) {
        notificationGroup
            .createNotification(
                "Script Shortcut",
                escapeString(message), notificationType, null
            )
            .notify(null)
    }

    private fun escapeString(string: String): String {
        return string.replace("\n".toRegex(), "\n<br />")
    }
}