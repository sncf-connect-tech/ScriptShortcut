package sncf.oui.scriptshortcut

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import sncf.oui.scriptshortcut.settings.SettingsDialog
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class DebugPanel(private val project: Project) : Configurable {

    var mainPanel: JPanel? = null

    var debugExplainationLabel: JLabel? = null

    override fun isModified(): Boolean {
        return true
    }

    override fun getDisplayName(): String {
        return "Script Shortcut"
    }

    override fun apply() {
        val dialog = SettingsDialog(project)
        dialog.show()
    }

    override fun createComponent(): JComponent? {
        return mainPanel
    }


}
