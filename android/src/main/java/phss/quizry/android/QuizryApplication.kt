package phss.quizry.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import phss.quizry.android.data.login.LoginDataSource
import phss.quizry.android.data.login.LoginRepository

val appModule = module {
    single { LoginRepository(LoginDataSource()) }
}

class QuizryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@QuizryApplication)
            modules(appModule)
        }
    }

}