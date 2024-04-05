package net.kingchev.catalyst.ru.discord.context.service

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.discord.context.model.ComponentContext
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import java.util.Optional

interface ContextService {
    fun getContext(id: String): Optional<ComponentContext>

    fun setContext(context: ComponentContext)

    companion object {
        fun createMessageContext(event: MessageReceivedEvent, guildLocale: String, userLocale: String, args: List<String>): MessageContext {
            val message = event.message

            var guild: Guild? = null
            try {
                guild = message.guild
            } catch (_: IllegalStateException) {}

            return MessageContext(
                message = message,
                guild = guild,
                author = message.author,
                authorMember = message.member,
                args = args,
                guildLocale = guildLocale,
                userLocale = userLocale
            )
        }

        fun createSlashContext(event: SlashCommandInteractionEvent, guildLocale: String, userLocale: String): SlashContext {
            val interaction = event.interaction

            var guild: Guild? = null
            try {
                guild = interaction.guild
            } catch (_: IllegalStateException) {}

            return SlashContext(
                interaction = interaction,
                guild = guild,
                author = interaction.user,
                authorMember = interaction.member,
                options = event.interaction.options,
                guildLocale = guildLocale,
                userLocale = userLocale
            )
        }

        fun createComponentContext(componentId: String, context: MessageContext, args: List<String>): ComponentContext {
            return ComponentContext(
                id = componentId,
                message = context.message,
                interaction = null,
                guild = context.guild,
                author = context.author,
                guildLocale = context.guildLocale,
                userLocale = context.userLocale,
                objects = args
            )
        }

        fun createComponentContext(componentId: String, context: SlashContext, args: List<String>): ComponentContext {
            return ComponentContext(
                id = componentId,
                message = null,
                interaction = context.interaction,
                guild = context.guild,
                author = context.author,
                guildLocale = context.guildLocale,
                userLocale = context.userLocale,
                objects = args
            )
        }
    }
}