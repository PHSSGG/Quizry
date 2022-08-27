package phss.quizry.user

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import phss.quizry.database.DatabaseManager
import phss.quizry.user.data.Accounts
import phss.quizry.user.data.domain.UserAccountEntity
import phss.quizry.user.type.CredentialsCheckType

class UserManager(
    private val databaseManager: DatabaseManager
) {

    fun checkCredentials(username: String, password: String): CredentialsCheckType {
        val account = loadUserByUsername(username) ?: return CredentialsCheckType.INVALID_USERNAME
        return if (account.password == password) CredentialsCheckType.VALID else CredentialsCheckType.WRONG_PASSWORD
    }

    fun createNewUser(username: String, password: String) = transaction {
        UserAccountEntity.new {
            this.username = username
            this.password = password
            this.plays = 0L
            this.coins = 0L
        }
    }

    fun deleteUser(username: String) {
        loadUserByUsername(username)?.delete()
    }

    fun loadUserByUsername(username: String) = loadUser { Accounts.username eq username }
    fun loadUserById(userId: Int) = loadUser { Accounts.id eq userId }

    private fun loadUser(query: SqlExpressionBuilder.() -> Op<Boolean>) = transaction {
        SchemaUtils.create(Accounts)
        UserAccountEntity.find(query).firstOrNull()
    }

}