package phss.quizry.quiz.data

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Quizzes : IntIdTable() {
    val category: Column<String> = text("category")
    val creator: Column<String> = text("creator") //TODO("Change this to reference")
    val title: Column<String> = text("title")
    val description: Column<String> = text("description")
    val questions: Column<String> = text("questions")
    val answers: Column<String> = text("answers")
}