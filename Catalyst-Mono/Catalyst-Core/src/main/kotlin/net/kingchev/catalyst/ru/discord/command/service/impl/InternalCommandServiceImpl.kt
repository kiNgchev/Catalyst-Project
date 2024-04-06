package net.kingchev.catalyst.ru.discord.command.service.impl

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.discord.command.service.InternalCommandService
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import net.kingchev.catalyst.ru.discord.handler.ErrorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InternalCommandServiceImpl : InternalCommandService {
    @Autowired
    private lateinit var errorHandler: ErrorHandler

    override fun fail(context: MessageContext, status: CommandStatus): CommandStatus {
        context.message.addReaction(Emoji.fromUnicode("❌")).queue()
        errorHandler.handleError(context, status)
        return status
    }

    override fun fail(context: SlashContext, status: CommandStatus): CommandStatus {
        errorHandler.handleError(context, status)
        return status
    }

    override fun ok(context: MessageContext, status: CommandStatus): CommandStatus {
        context.message.addReaction(Emoji.fromUnicode("✅")).queue()
        return status
    }

    override fun ok(context: SlashContext, status: CommandStatus): CommandStatus {
        return status
    }
}