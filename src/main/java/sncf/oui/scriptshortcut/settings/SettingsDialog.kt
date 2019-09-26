package sncf.oui.scriptshortcut.settings

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import sncf.oui.scriptshortcut.DebugPanel
import sncf.oui.scriptshortcut.SettingsDialogPanel
import javax.swing.JComponent


class SettingsDialog(val project: Project) : DialogWrapper(true) {

    init {
        isModal = true

        init()
    }

    override fun doOKAction() {
        super.doOKAction()

    }


    override fun createCenterPanel(): JComponent? {

        val panel = SettingsDialogPanel(project)

        panel.initData()

        return panel.mainPanel
    }


}