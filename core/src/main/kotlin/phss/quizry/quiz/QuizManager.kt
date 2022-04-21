package phss.quizry.quiz

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import phss.quizry.database.DatabaseManager
import phss.quizry.quiz.data.Quizzes
import phss.quizry.quiz.data.domain.Quiz
import phss.quizry.user.data.domain.UserAccount

class QuizManager(
    private val databaseManager: DatabaseManager
) {

    fun loadQuizzes(): List<Quiz>  = transaction {
        SchemaUtils.create(Quizzes)
        Quiz.all().toList()
    }

    fun createQuiz(category: String, creator: Int, title: String, description: String, questions: List<Quiz.QuizQuestion>, answers: List<Quiz.QuizAnswer>) = transaction {
        Quiz.new {
            this.category = category
            this.creator = UserAccount[creator]
            this.title = title
            this.description = description
            this.questions = Json.encodeToString(questions)
            this.answers = Json.encodeToString(answers)
        }
    }

    fun deleteQuiz(quizId: Int) {
        Quiz.findById(quizId)?.delete()
    }

}