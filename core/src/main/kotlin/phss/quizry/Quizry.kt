package phss.quizry

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import phss.quizry.config.provider.impl.DatabaseConfig
import phss.quizry.database.DatabaseManager
import phss.quizry.quiz.QuizManager
import phss.quizry.quiz.data.domain.Quiz
import phss.quizry.service.startQuizService
import phss.quizry.service.startUserService
import phss.quizry.user.UserManager

fun main() {
    val databaseManager = DatabaseManager(DatabaseConfig().get()).also { it.connect() }
    val quizManager = QuizManager(databaseManager)
    val userManager = UserManager(databaseManager)

    embeddedServer(Netty, port = 8080) {
        startQuizService(quizManager)
        startUserService(userManager)
    }.start(true)
}