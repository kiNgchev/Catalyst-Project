package net.kingchev.catalyst.ru.discord.config

import net.kingchev.catalyst.ru.core.config.CoreConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    CoreConfiguration::class
)
@ComponentScan(basePackages = ["net.kingchev.catalyst.ru.discord"])
class WorkerConfiguration {
    @Autowired
    lateinit var workerProperties: WorkerProperties
}