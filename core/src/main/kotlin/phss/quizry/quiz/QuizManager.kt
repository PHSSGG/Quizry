package phss.quizry.quiz

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import phss.quizry.database.DatabaseManager
import phss.quizry.quiz.data.Quizzes
import phss.quizry.quiz.data.domain.Quiz

class QuizManager(
    private val databaseManager: DatabaseManager
) {

    fun loadQuizzes(): List<Quiz>  = transaction {
        SchemaUtils.create(Quizzes)
        Quiz.all().toList()
    }

}