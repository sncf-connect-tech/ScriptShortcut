package sncf.oui.scriptshortcut.settings

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import sncf.oui.scriptshortcut.Settings
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextArea
import java.awt.Dimension
import javax.swing.*


class SettingsDialog(val project: Project) : DialogWrapper(true) {

    init {
        isModal = true

        init()
    }

    override fun doOKAction() {
        super.doOKAction()

    }


    override fun createCenterPanel(): JComponent? {

        return Settings(project).mainPanel
    }


}