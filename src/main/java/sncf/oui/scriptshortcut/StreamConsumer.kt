package sncf.oui.scriptshortcut

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class StreamConsumer(private var inputStream: InputStream, private val error: Boolean = false) : Thread() {
    override fun run() = try {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = ""
        while (line != null) {
            line = bufferedReader.readLine()
            NotificationHelper.info(line)
        }
    } catch (ioe: IOException) {
        ioe.printStackTrace()
    }
}