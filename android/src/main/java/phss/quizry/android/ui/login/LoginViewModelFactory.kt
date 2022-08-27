package phss.quizry.android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phss.quizry.android.data.login.LoginDataSource
import phss.quizry.android.data.login.LoginRepository

class LoginViewModelFactory(
    val loginRepository: LoginRepository = LoginRepository(dataSource = LoginDataSource())
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}