package phss.quizry.quiz.data.domain

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import phss.quizry.quiz.data.Quizzes
import phss.quizry.user.data.domain.UserAccount

class Quiz(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Quiz>(Quizzes)

    var category by Quizzes.category
    var creator by UserAccount referencedOn Quizzes.creator
    var title by Quizzes.title
    var description by Quizzes.description
    var questions by Quizzes.questions
    var answers by Quizzes.answers

    @Serializable
    class QuizQuestion(
        val position: Int,
        val title: String,
        val description: String,
        val points: Long
    )

    @Serializable
    class QuizAnswer(
        val position: Int,
        val title: String,
        val description: String,
        val requiredPoints: Long
    )

}