package net.kingchev.catalyst.ru.discord.command.service

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext

interface InternalCommandService {
    fun fail(context: MessageContext, status: CommandStatus): CommandStatus

    fun fail(context: SlashContext, status: CommandStatus): CommandStatus

    fun ok(context: MessageContext, status: CommandStatus): CommandStatus

    fun ok(context: SlashContext, status: CommandStatus): CommandStatus
}