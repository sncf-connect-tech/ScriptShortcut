package sncf.oui.scriptshortcut

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "ScriptShortcut", storages = [
        Storage(value = "scriptShortcutConfiguration.xml")
    ]
)
class UserConfiguration : PersistentStateComponent<UserConfiguration> {
    var scriptPath: String = ""
    var arguments: String = ""

    override fun getState(): UserConfiguration? {
        return this
    }

    override fun loadState(state: UserConfiguration) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(project: Project): UserConfiguration = project.getComponent(UserConfiguration::class.java)
    }
}