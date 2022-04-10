package phss.quizry.config.provider.impl

import phss.quizry.config.Config
import phss.quizry.config.provider.ConfigurationProvider

const val DATABASE_FILE = "database"
const val DATABASE_NAME = "Quizry"

class DatabaseConfig : ConfigurationProvider<DatabaseConfig>() {

    var hostname = "localhost"
    var port = 3306
    var username = ""
    var password = ""

    override fun get(): DatabaseConfig {
        val config = Config()
        val databaseFile = config.getOrCreateFile(DATABASE_FILE)
        val jsonObject = config.readConfigFile(databaseFile.inputStream())

        val mysqlCredentials = jsonObject.getJSONObject("mysql")
        hostname = mysqlCredentials.getString("hostname")
        port = mysqlCredentials.getInt("port")
        username = mysqlCredentials.getString("username")
        password = mysqlCredentials.getString("password")

        return this
    }

}