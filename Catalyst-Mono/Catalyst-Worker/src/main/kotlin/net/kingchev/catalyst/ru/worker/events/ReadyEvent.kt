package net.kingchev.catalyst.ru.worker.events

import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.kingchev.catalyst.ru.discord.config.WorkerProperties
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

@CatalystEvent(eventName = "readyEvent")
class ReadyEvent : ListenerAdapter(), Event {
    @Autowired
    private lateinit var workerProperties: WorkerProperties

    override fun onReady(event: ReadyEvent) {
        event.jda.presence.setPresence(
            OnlineStatus.ONLINE,
            Activity.streaming("${workerProperties.discord.prefix}help", "https://www.twitch.tv/k1nghev"),
            true
        )
        log.info("Shard #${event.jda.shardInfo.shardId}/${event.jda.shardManager?.shards?.count()} is connected!")
    }

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger(this::class.java)
    }
}