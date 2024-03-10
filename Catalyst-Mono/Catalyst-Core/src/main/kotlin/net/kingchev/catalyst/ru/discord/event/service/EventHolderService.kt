package net.kingchev.catalyst.ru.discord.event.service

import net.kingchev.catalyst.ru.discord.event.model.Event

interface EventHolderService {
    fun register(events: Array<Event>)

    fun getEvents(): List<Event>
}