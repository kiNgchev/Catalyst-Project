package net.kingchev.catalyst.ru.worker

import net.kingchev.catalyst.ru.core.localezied.CatalystMessageSource
import net.kingchev.catalyst.ru.discord.config.WorkerConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(
    WorkerConfiguration::class
)
@SpringBootApplication
class WorkerApplication {
    @Bean
    fun errorMessages() = CatalystMessageSource("catalyst-error")
}

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<WorkerApplication>(*args)
    }
}