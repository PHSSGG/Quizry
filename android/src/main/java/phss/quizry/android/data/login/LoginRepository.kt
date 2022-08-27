package phss.quizry.android.data.login

import phss.quizry.android.data.DataResult
import phss.quizry.android.data.domain.UserAccount

class LoginRepository(val dataSource: LoginDataSource) {

    var user: UserAccount? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, isRegister: Boolean): DataResult<UserAccount> {
        val result = dataSource.login(username, password, isRegister)

        if (result is DataResult.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: UserAccount) {
        this.user = loggedInUser
    }

}