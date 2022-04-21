package phss.quizry.user.data

import org.jetbrains.exposed.dao.id.IntIdTable

object Accounts : IntIdTable() {
    val username = text("username")
    val password = text("password")
    val plays = long("plays")
    val coins = long("coins")
}