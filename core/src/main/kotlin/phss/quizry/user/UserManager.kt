package phss.quizry.user

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import phss.quizry.database.DatabaseManager
import phss.quizry.user.data.Accounts
import phss.quizry.user.data.domain.UserAccount
import phss.quizry.user.type.CredentialsCheckType

class UserManager(
    private val databaseManager: DatabaseManager
) {

    fun checkCredentials(username: String, password: String): CredentialsCheckType {
        val account = loadUserByUsername(username) ?: return CredentialsCheckType.INVALID_USERNAME
        return if (account.password == password) CredentialsCheckType.VALID else CredentialsCheckType.WRONG_PASSWORD
    }

    fun createNewUser(username: String, password: String) = transaction {
        UserAccount.new {
            this.username = username
            this.password = password
        }
    }

    fun deleteUser(username: String) {
        loadUserByUsername(username)?.delete()
    }

    fun loadUserByUsername(username: String) = loadUser { Accounts.username eq username }
    fun loadUserById(userId: Int) = loadUser { Accounts.id eq userId }

    private fun loadUser(query: SqlExpressionBuilder.() -> Op<Boolean>) = transaction {
        UserAccount.find(query).firstOrNull()
    }

}