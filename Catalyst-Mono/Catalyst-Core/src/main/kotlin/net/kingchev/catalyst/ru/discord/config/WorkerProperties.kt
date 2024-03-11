package net.kingchev.catalyst.ru.discord.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import kotlin.properties.Delegates

@Component
@Scope(scopeName = "singleton")
@PropertySources(
    PropertySource("classpath:/discord.yaml"),
)
@ConfigurationProperties("discord-properties")
class WorkerProperties {
    @Autowired
    lateinit var discord: Discord

    @Component
    class Discord(
        @Value("\${token}")
        val token: String,
    ) {
        val shardsCount: Int = 2
    }
}