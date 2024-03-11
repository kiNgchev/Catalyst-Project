package net.kingchev.catalyst.ru.discord.command.service

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.core.model.CommandStatus

interface InternalCommandService {
    fun fail(event: MessageReceivedEvent, status: CommandStatus): CommandStatus

    fun ok(event: MessageReceivedEvent, status: CommandStatus): CommandStatus
}