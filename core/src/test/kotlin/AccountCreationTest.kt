import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import phss.quizry.user.data.domain.UserAccount
import java.io.IOException

const val API_URL = "http://localhost:8080"
const val REGISTER_URL = "$API_URL/register"
const val LOGIN_URL = "$API_URL/login"

class AccountCreationTest {

    val username = "Testing"
    val password = "123456"

    @Test
    fun create_account() {
        val invoke = execute(true)
        if (invoke is DataResult.Success) {
            assertEquals("Testing", invoke.data.username)
            return
        }

        assertEquals("Username already exists", (invoke as DataResult.Error).exception.message)
    }

    @Test
    fun login_account() {
        val invoke = execute()

        println(invoke is DataResult.Success)

        if (invoke is DataResult.Success) {
            assertEquals("Testing", invoke.data.username)
            return
        }

        assertEquals("Password is incorrect", (invoke as DataResult.Error).exception.message)
    }

    private fun execute(isRegister: Boolean = false): DataResult<UserAccount> {
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
                        parameters.append("password", this@AccountCreationTest.password)
                    }
                }

                println(response.request.call.response.status)
                println(response.status.value)
                if (response.status.value !in 201..202) {
                    return@runBlocking when (response.status) {
                        HttpStatusCode.NotFound -> DataResult.Error(IOException("Username doesn't exists", Exception()))
                        HttpStatusCode.Unauthorized -> DataResult.Error(IOException("Password is incorrect", Exception()))
                        else -> DataResult.Error(IOException("Username already exists", Exception()))
                    }
                }

                DataResult.Success(response.body())
            }
        } catch (ex: Exception) { DataResult.Error(ex) }
    }

}

sealed class DataResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()

}