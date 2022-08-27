package phss.quizry

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import phss.quizry.config.provider.impl.DatabaseConfig
import phss.quizry.database.DatabaseManager
import phss.quizry.quiz.QuizManager
import phss.quizry.service.startQuizService
import phss.quizry.service.startUserService
import phss.quizry.user.UserManager

fun main() {
    val databaseManager = DatabaseManager(DatabaseConfig().get()).also { it.connect() }
    val quizManager = QuizManager(databaseManager)
    val userManager = UserManager(databaseManager)

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson()
        }

        startQuizService(quizManager)
        startUserService(userManager)
    }.start(true)
}