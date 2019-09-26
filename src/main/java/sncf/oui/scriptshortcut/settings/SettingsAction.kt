package sncf.oui.scriptshortcut.settings

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class SettingsAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val dialog = SettingsDialog(event.project!!)
        dialog.show()
    }


}