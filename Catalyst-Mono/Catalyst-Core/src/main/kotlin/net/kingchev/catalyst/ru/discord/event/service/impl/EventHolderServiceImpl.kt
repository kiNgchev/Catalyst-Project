package net.kingchev.catalyst.ru.discord.event.service.impl

import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event
import net.kingchev.catalyst.ru.discord.event.service.EventHolderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope("singleton")
class EventHolderServiceImpl : EventHolderService {

    private val events: HashMap<String, Event> = hashMapOf()

    @Autowired
    override fun register(events: Array<Event>) {
        events.forEach {
            try {
                val annotation = it.javaClass.getAnnotation(CatalystEvent::class.java)
                if (it !is ListenerAdapter) return@forEach
                this.events[annotation.eventName] = it
                log.info("Event with name `${annotation.eventName} successfully loaded!`")

            } catch (_: NullPointerException) {
                return@forEach
            }
        }
    }

    override fun getEvents(): List<Event> {
        return events.values.toList()
    }

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger(this::class.java)
    }
}