package net.kingchev.catalyst.ru.discord.handler

import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext

interface ErrorHandler {
    fun handleError(context: MessageContext, error: CommandStatus)

    fun handleError(context: SlashContext, error: CommandStatus)
}