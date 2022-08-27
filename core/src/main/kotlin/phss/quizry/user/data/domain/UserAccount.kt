package phss.quizry.user.data.domain

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import phss.quizry.user.data.Accounts

class UserAccountEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UserAccountEntity>(Accounts)

    var username by Accounts.username
    var password by Accounts.password
    var plays by Accounts.plays
    var coins by Accounts.coins

    fun toUserAccount() = UserAccount(username, plays, coins)

}

@kotlinx.serialization.Serializable
data class UserAccount(
    val username: String,
    val plays: Long,
    val coins: Long
)