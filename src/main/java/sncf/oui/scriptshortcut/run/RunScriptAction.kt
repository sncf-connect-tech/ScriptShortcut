package sncf.oui.scriptshortcut.run

import com.android.tools.idea.gradle.project.sync.GradleSyncState
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import sncf.oui.scriptshortcut.NotificationHelper
import sncf.oui.scriptshortcut.StreamConsumer
import sncf.oui.scriptshortcut.UserConfiguration
import java.io.File


class RunScriptAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)

        if (project == null) {
            NotificationHelper.error("Error : unable to initialize plugin")
            return
        }

        if (isGradleSyncInProgress(project)) {
            NotificationHelper.error("Abort script : Gradle sync is running")
            return
        }

        val scriptAbsolutePath = UserConfiguration.getInstance(project).scriptPath
        val arguments = UserConfiguration.getInstance(project).arguments
        val projectFolder = project.basePath ?: ""

        ApplicationManager.getApplication().executeOnPooledThread {
            ApplicationManager.getApplication().runReadAction {
                executeScript(scriptAbsolutePath, arguments, projectFolder)
            }
        }
    }

    private fun isGradleSyncInProgress(project: Project): Boolean {
        return try {
            GradleSyncState.getInstance(project).isSyncInProgress
        } catch (t: Throwable) {
            //Couldn't determine if a gradle sync is in progress"
            false
        }
    }

    private fun executeScript(scriptAbsolutePath: String, arguments: String, projectFolder: String) {
        val absolutePath = File(scriptAbsolutePath)
        if (!absolutePath.isFile) {
            NotificationHelper.error("Abort : script file not found $absolutePath")
            return
        }

        NotificationHelper.info("--- Start running ${absolutePath.name} ---")
        Runtime.getRuntime()
            .exec(
                arrayOf("/bin/sh", "-c", "$scriptAbsolutePath $arguments"),
                arrayOf(projectFolder),
                File(projectFolder)
            )?.let { process ->
                consumeProcess(process)
            }
    }

    private fun consumeProcess(process: Process) {
        StreamConsumer(process.inputStream).start()
        StreamConsumer(process.errorStream, true).start()

        val exitStatus = process.waitFor()
        if (exitStatus == 0) {
            NotificationHelper.info("--- Script finished successfully ---")
        } else {
            NotificationHelper.error("Script finished with exit value $exitStatus")
        }
    }


}
