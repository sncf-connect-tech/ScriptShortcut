package sncf.oui.scriptshortcut.settings

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import sncf.oui.scriptshortcut.UserConfiguration
import javax.swing.JComponent


class SettingsDialog(private val project: Project) : DialogWrapper(true) {

    private val dialogPanel = SettingsDialogPanel(project)

    init {
        isModal = true

        init()
    }

    override fun doOKAction() {
        super.doOKAction()

        saveUserInput()
    }

    private fun saveUserInput() {
        val config = UserConfiguration.getInstance(project)
        config.scriptPath = dialogPanel.getPathField()
        config.arguments = dialogPanel.getArguments()
    }

    override fun createCenterPanel(): JComponent? {
        val config = UserConfiguration.getInstance(project)
        dialogPanel.init(config.scriptPath, config.arguments)

        return dialogPanel.mainPanel
    }


}