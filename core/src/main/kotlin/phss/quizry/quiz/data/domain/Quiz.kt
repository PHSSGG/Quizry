package phss.quizry.quiz.data.domain

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import phss.quizry.quiz.data.Quizzes

class Quiz(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Quiz>(Quizzes)

    var category by Quizzes.category
    var creator by Quizzes.creator
    var title by Quizzes.title
    var description by Quizzes.description
    var serializedQuestionsList by Quizzes.questions
    var serializedAnswersList by Quizzes.answers

    //var questions = retrieveListBySerialized<QuizQuestion>(serializedQuestionsList)
    //var answers = retrieveListBySerialized<QuizAnswer>(serializedAnswersList)

    fun <T> retrieveListBySerialized(serializedList: String): List<T> {
        TODO("Deserialized list and return it")
    }

    inner class QuizQuestion(
        val position: Int,
        val title: String,
        val description: String,
        val points: Long
    )

    inner class QuizAnswer(
        val position: Int,
        val title: String,
        val description: String,
        val requiredPoints: Long
    )

}