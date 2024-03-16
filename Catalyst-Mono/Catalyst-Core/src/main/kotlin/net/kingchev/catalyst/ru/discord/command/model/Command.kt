package net.kingchev.catalyst.ru.discord.command.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Command {
    fun getAnnotation(): CatalystCommand {
        var annotation: CatalystCommand
        try {
            annotation = javaClass.getAnnotation(CatalystCommand::class.java)
        } catch (_: NullPointerException) {
            log.error("Annotation in command ${javaClass.name} is not defined")
            annotation = CatalystCommand(key = "none", description = "none", group = "none")
        }
        return annotation
    }

    fun build(): SlashCommandData

    fun execute(event: MessageReceivedEvent, context: MessageContext): Boolean

    fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean

    fun isAvailable(event: MessageReceivedEvent, context: MessageContext): CommandStatus

    fun isAvailable(event: SlashCommandInteractionEvent, context: SlashContext): CommandStatus

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger("commandLogger")
    }
}