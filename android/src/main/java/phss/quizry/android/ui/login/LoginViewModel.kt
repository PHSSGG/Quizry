package phss.quizry.android.ui.login

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

import phss.quizry.android.R
import phss.quizry.android.data.login.LoginRepository
import phss.quizry.android.data.DataResult
import phss.quizry.android.ui.login.result.LoginFormState
import phss.quizry.android.ui.login.result.LoginResult
import phss.quizry.android.ui.login.view.LoggedInUserView

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String, isRegister: Boolean) {
        val result = loginRepository.login(username, password, isRegister)

        if (result is DataResult.Success) {
            _loginResult.value = LoginResult(success = LoggedInUserView(username = result.data.username))
        } else {
            _loginResult.value = LoginResult(error = (result as DataResult.Error).exception.message ?: Resources.getSystem().getString(R.string.login_failed))
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 4
    }

}