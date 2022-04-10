package phss.quizry.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import phss.quizry.config.provider.impl.DATABASE_NAME
import phss.quizry.config.provider.impl.DatabaseConfig

class DatabaseManager(
    private val config: DatabaseConfig
) {

    lateinit var dataSource: HikariDataSource
    lateinit var database: Database

    fun connect() {
        dataSource = HikariDataSource(HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://${config.hostname}:${config.port}/${DATABASE_NAME}"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = config.username
            password = config.password
        })

        database = Database.connect(dataSource)
    }

}