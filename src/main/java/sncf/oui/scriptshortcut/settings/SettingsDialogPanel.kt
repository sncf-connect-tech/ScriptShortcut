package sncf.oui.scriptshortcut.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import java.io.File
import javax.swing.*
import javax.swing.filechooser.FileNameExtensionFilter


class SettingsDialogPanel(private val project: Project) : Configurable {

    private var pathLabel: JLabel? = null
    private var argumentLabel: JLabel? = null
    private var shortcutLabel: JLabel? = null

    private var pathField: JLabel? = null
    private var argumentField: JTextField? = null
    private var shortcutField: JLabel? = null

    private var editPathButton: JButton? = null

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

        editPathButton?.addActionListener {
            val fileChooser = JFileChooser()
            val filter = FileNameExtensionFilter("Shell script", "sh")
            fileChooser.fileFilter = filter
            fileChooser.currentDirectory = File(project.basePath ?: "")

            val resultStatus = fileChooser.showOpenDialog(mainPanel)
            if (resultStatus == JFileChooser.APPROVE_OPTION) {
                pathField?.text = fileChooser.selectedFile.absolutePath
            }
        }
    }


    fun getPathField(): String {
        return pathField?.text ?: ""
    }

    fun getArguments(): String {
        return argumentField?.text ?: ""
    }

}
