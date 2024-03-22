package net.kingchev.catalyst.ru.discord.config

import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import net.kingchev.catalyst.ru.core.config.CoreConfiguration
import net.kingchev.catalyst.ru.discord.command.service.CommandHolderService
import net.kingchev.catalyst.ru.discord.event.service.EventHolderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope

@Configuration
@Import(
    CoreConfiguration::class
)
@ComponentScan(basePackages = ["net.kingchev.catalyst.ru.discord"])
class WorkerConfiguration {
    companion object {
        const val JDA_BEAN_NAME = "jda"
    }

    @Autowired
    lateinit var workerProperties: WorkerProperties

    @Autowired
    lateinit var commandHolder: CommandHolderService

    @Autowired
    lateinit var eventHolder: EventHolderService

    @Lazy(false)
    @Scope("singleton")
    @Bean(name = [JDA_BEAN_NAME])
    fun jda(): ShardManager {
        val jda = DefaultShardManagerBuilder
            .createDefault(workerProperties.discord.token)
            .addEventListeners(eventHolder.getEvents())
            .setShardsTotal(workerProperties.discord.shardsCount)
            .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
            .build()

        jda.shards.stream()
            .parallel()
            .forEach { commandHolder.registerSlashCommand(it) }
        return jda
    }
}