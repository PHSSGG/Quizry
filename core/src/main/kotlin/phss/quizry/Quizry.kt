package phss.quizry

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import phss.quizry.config.provider.impl.DatabaseConfig
import phss.quizry.database.DatabaseManager

fun main() {
    val databaseManager = DatabaseManager(DatabaseConfig().get()).connect()

    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") { call.respondText("Hello World!") }
        }
    }.start(true)
}