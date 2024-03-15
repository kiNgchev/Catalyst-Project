package net.kingchev.catalyst.ru.discord.command.service.impl

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.discord.command.service.InternalCommandService
import net.kingchev.catalyst.ru.discord.handler.ErrorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InternalCommandServiceImpl : InternalCommandService {
    @Autowired
    private lateinit var errorHandler: ErrorHandler

    override fun fail(event: MessageReceivedEvent, status: CommandStatus): CommandStatus {
        event.message.addReaction(Emoji.fromUnicode("❌")).queue()
        errorHandler.handleError(event, status)
        return status
    }

    override fun fail(event: SlashCommandInteractionEvent, status: CommandStatus): CommandStatus {
        errorHandler.handleError(event, status)
        return status
    }

    override fun ok(event: MessageReceivedEvent, status: CommandStatus): CommandStatus {
        event.message.addReaction(Emoji.fromUnicode("✅")).queue()
        return status
    }

    override fun ok(event: SlashCommandInteractionEvent, status: CommandStatus): CommandStatus {
        return status
    }
}