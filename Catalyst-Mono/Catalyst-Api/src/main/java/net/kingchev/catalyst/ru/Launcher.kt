package net.kingchev.catalyst.ru

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatalystApi

object Launcher {
    fun main(args: Array<String>) {
        runApplication<CatalystApi>(*args)
    }
}