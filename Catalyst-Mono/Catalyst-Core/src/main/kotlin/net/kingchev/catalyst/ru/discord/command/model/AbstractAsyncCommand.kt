package net.kingchev.catalyst.ru.discord.command.model

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractAsyncCommand : AbstractCommand() {
    override fun execute(event: MessageReceivedEvent, context: MessageContext): Boolean = GlobalScope.async {
        executeAsync(event, context)
    }.start()

    override fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean = GlobalScope.async {
        executeAsync(event, context)
    }.start()

    protected abstract fun executeAsync(event: MessageReceivedEvent, context: MessageContext): Boolean

    protected abstract fun executeAsync(event: SlashCommandInteractionEvent, context: SlashContext): Boolean

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger(this::class.java)
    }
}