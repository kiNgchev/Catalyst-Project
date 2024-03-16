package net.kingchev.catalyst.ru.discord.handler

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.core.model.CommandStatus

interface ErrorHandler {
    fun handleError(event: MessageReceivedEvent, error: CommandStatus)

    fun handleError(event: SlashCommandInteractionEvent, error: CommandStatus)
}