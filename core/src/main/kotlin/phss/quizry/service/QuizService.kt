package phss.quizry.service

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import phss.quizry.quiz.QuizManager

fun Application.startQuizService(quizManager: QuizManager) = routing {
    post("create") {
        val category = call.request.queryParameters["category"] ?: return@post
        val creator = call.request.queryParameters["creator"] ?: return@post
        val title = call.request.queryParameters["title"] ?: return@post
        val description = call.request.queryParameters["description"] ?: return@post
        val questions = call.request.queryParameters["questions"] ?: return@post
        val answers = call.request.queryParameters["answers"] ?: return@post

        quizManager.createQuiz(category, creator.toInt(), title, description, questions, answers)
        call.respond(HttpStatusCode.Created)
    }
    post("delete") {
        val id = call.request.queryParameters["id"] ?: return@post

        quizManager.deleteQuiz(id.toInt())
        call.respond(HttpStatusCode.OK)
    }
    get("getQuizzes") {
        val quizzes = quizManager.loadQuizzes()
        call.respond(HttpStatusCode.OK)
    }
}