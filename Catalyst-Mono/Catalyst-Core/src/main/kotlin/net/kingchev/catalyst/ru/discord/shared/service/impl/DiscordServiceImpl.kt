package net.kingchev.catalyst.ru.discord.shared.service.impl

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import net.kingchev.catalyst.ru.core.config.CoreProperties
import net.kingchev.catalyst.ru.discord.command.service.CommandHolderService
import net.kingchev.catalyst.ru.discord.config.WorkerProperties
import net.kingchev.catalyst.ru.discord.event.service.EventHolderService
import net.kingchev.catalyst.ru.discord.shared.service.DiscordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
@Lazy(value = false)
class DiscordServiceImpl(
    @Autowired val jda: ShardManager
) : DiscordService {

    @Autowired
    private lateinit var workerProperties: WorkerProperties;

    @Autowired
    lateinit var commandHolder: CommandHolderService

    @Autowired
    lateinit var eventHolder: EventHolderService

    @PostConstruct
    fun init() {}

    @PreDestroy
    fun destroy() {}
}