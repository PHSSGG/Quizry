package phss.quizry.android.ui.login.result

import phss.quizry.android.ui.login.view.LoggedInUserView

data class LoginResult (
    val success: LoggedInUserView? = null,
    val error: String? = null
)