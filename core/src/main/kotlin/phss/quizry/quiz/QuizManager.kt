package phss.quizry.quiz

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

    fun loadQuizById(quizId: Int) = transaction { Quiz.findById(quizId) }

    fun createQuiz(category: String, creator: Int, title: String, description: String, questions: String, answers: String) = transaction {
        Quiz.new {
            this.category = category
            this.creator = UserAccount[creator]
            this.title = title
            this.description = description
            this.questions = questions
            this.answers = answers
        }
    }

    fun deleteQuiz(quizId: Int) {
        Quiz.findById(quizId)?.delete()
    }

}