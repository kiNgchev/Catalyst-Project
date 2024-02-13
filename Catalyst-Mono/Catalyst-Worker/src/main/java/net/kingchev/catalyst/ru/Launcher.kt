package net.kingchev.catalyst.ru

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatalystWorker

object Launcher {
    fun main(args: Array<String>) {
        runApplication<CatalystWorker>(*args)
    }
}