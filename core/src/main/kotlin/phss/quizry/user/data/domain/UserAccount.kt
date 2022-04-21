package phss.quizry.user.data.domain

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import phss.quizry.user.data.Accounts

class UserAccount(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UserAccount>(Accounts)

    var username by Accounts.username
    var password by Accounts.password
    var plays by Accounts.plays
    val coins by Accounts.coins

}