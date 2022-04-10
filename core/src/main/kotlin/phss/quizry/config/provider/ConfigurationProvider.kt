package phss.quizry.config.provider

abstract class ConfigurationProvider <T> {
    abstract fun get(): T
}