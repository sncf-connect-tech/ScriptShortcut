package sncf.oui.scriptshortcut.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import sncf.oui.scriptshortcut.UserConfiguration
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class SettingsDialogPanel(private val project: Project) : Configurable {

    private var pathLabel: JLabel? = null
    private var argumentLabel: JLabel? = null
    private var shortcutLabel: JLabel? = null

    private var pathField: JTextField? = null
    private var argumentField: JTextField? = null
    private var shortcutField: JLabel? = null

    public var mainPanel: JPanel? = null

    override fun isModified(): Boolean {
        return false
    }

    override fun getDisplayName(): String {
        return "Script Shortcut"
    }

    override fun apply() {
    }


    override fun createComponent(): JComponent? {
        return mainPanel
    }

    fun init(scriptPath: String, arguments: String) {
        pathField?.text = scriptPath
        argumentField?.text = arguments
    }


    fun getPathField(): String {
        return pathField?.text ?: ""
    }

    fun getArguments(): String {
        return argumentField?.text ?: ""
    }

}
