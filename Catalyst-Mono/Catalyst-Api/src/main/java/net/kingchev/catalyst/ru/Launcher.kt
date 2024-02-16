package net.kingchev.catalyst.ru

import net.kingchev.catalyst.ru.config.CommonConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    CommonConfiguration::class
)
class CatalystApi

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<CatalystApi>(*args)
    }
}