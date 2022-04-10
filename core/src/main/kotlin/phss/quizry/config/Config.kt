package phss.quizry.config

import org.json.JSONObject
import java.io.File
import java.io.InputStream
import java.nio.file.Files

class Config {

    fun getOrCreateFile(fileName: String): File {
        val folder = File("config")
        if (!folder.exists()) folder.mkdirs()

        val file = File(folder, "$fileName.json")
        if (!file.exists()) {
            val inputStream = javaClass.getResource("/$fileName.json").openStream()
            Files.copy(inputStream, file.toPath())
        }

        return file
    }

    fun readConfigFile(inputStream: InputStream): JSONObject {
        return JSONObject(inputStream.bufferedReader().use { it.readText() })
    }

}