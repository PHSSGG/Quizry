package phss.quizry.service

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import phss.quizry.user.UserManager
import phss.quizry.user.type.CredentialsCheckType

fun Application.startUserService(userManager: UserManager) = routing {
    post("/login") {
        val username = call.request.queryParameters["username"] ?: return@post
        val password = call.request.queryParameters["password"] ?: return@post

        when (userManager.checkCredentials(username, password)) {
            CredentialsCheckType.INVALID_USERNAME -> call.respond(HttpStatusCode.NotFound)
            CredentialsCheckType.WRONG_PASSWORD -> call.respond(HttpStatusCode.Unauthorized)
            else -> call.respond(HttpStatusCode.Accepted)
        }
    }
    post("/register") {
        val username = call.request.queryParameters["username"] ?: return@post
        val password = call.request.queryParameters["password"] ?: return@post

        if (userManager.loadUserByUsername(username) != null) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        userManager.createNewUser(username, password)
        call.respond(HttpStatusCode.Created)
    }
    post("/delete") {
        val username = call.request.queryParameters["username"] ?: return@post

        if (userManager.loadUserByUsername(username) == null) {
            call.respond(HttpStatusCode.NotFound)
            return@post
        }

        userManager.deleteUser(username)
        call.respond(HttpStatusCode.OK)
    }
    get("/getUserByName") {
        val username = call.request.queryParameters["username"] ?: return@get

        val user = userManager.loadUserByUsername(username)
        if (user == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(HttpStatusCode.OK)
    }
    get("/getUserById") {
        val id = call.request.queryParameters["id"] ?: return@get

        val user = userManager.loadUserById(id.toInt())
        if (user == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(HttpStatusCode.OK)
    }
}