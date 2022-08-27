package phss.quizry.android.data.login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.runBlocking
import phss.quizry.android.data.DataResult
import phss.quizry.android.data.domain.UserAccount
import java.io.IOException

const val API_URL = "http:/0.0.0.0:8080"
const val REGISTER_URL = "$API_URL/register"
const val LOGIN_URL = "$API_URL/login"

class LoginDataSource {

    fun login(username: String, password: String, isRegister: Boolean = false): DataResult<UserAccount> {
        return try {
            runBlocking {
                val client = HttpClient() {
                    install(ContentNegotiation) {
                        gson()
                    }
                }
                val response = client.post(if (isRegister) REGISTER_URL else LOGIN_URL) {
                    url {
                        parameters.append("username", username)
                        parameters.append("password", password)
                    }
                }

                if (response.status.value !in 201..202) {
                    return@runBlocking when (response.status) {
                        HttpStatusCode.NotFound -> DataResult.Error(IOException("Username doesn't exists", Exception()))
                        HttpStatusCode.Unauthorized -> DataResult.Error(IOException("Password is incorrect", Exception()))
                        else -> DataResult.Error(IOException("Username already exists", Exception()))
                    }
                }

                val model = response.body<UserAccount>()
                client.close()

                DataResult.Success(model)
            }
        } catch (e: Throwable) {
            DataResult.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

}