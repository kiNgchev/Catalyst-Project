package net.kingchev.catalyst.ru.api

import net.kingchev.catalyst.ru.api.config.ApiConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    ApiConfiguration::class
)
class ApiApplication

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<ApiApplication>(*args)
    }
}