package net.kingchev.catalyst.ru.worker.commands.misc

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.DiscordLocale
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import net.kingchev.catalyst.ru.discord.command.model.AbstractCommand
import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import java.util.Locale

@CatalystCommand(
    key = "ping",
    description = "discord.command.metadata.ping.description",
    aliases = ["pong", "pi"],
    group = "discord.command.group.misc"
)
class PingCommand : AbstractCommand() {
    override fun build(): SlashCommandData {
        val locales = hashMapOf<DiscordLocale, String>()
        locales[DiscordLocale.RUSSIAN] = localeService.getMessage(getAnnotation().description, "ru")
        val description = localeService.getMessage(getAnnotation().description, LocaleUtils.DEFAULT.language)
        val builder = Commands.slash(
            getAnnotation().key,
            description
        )
            .setDescriptionLocalizations(locales)
        return builder
    }

    override fun execute(event: MessageReceivedEvent, context: MessageContext): Boolean {
        val locale = if (context.guild != null) context.locale else context.userLocale

        val restPing = event.jda.restPing.complete()
        val gatewayPing = event.jda.gatewayPing
        val embed = messageService.getBaseEmbed(localeService.getMessage("discord.command.ping.title", locale))
            .setDescription(localeService.getMessage("discord.command.ping.description", locale, restPing, gatewayPing))
            .build()
        event.message.replyEmbeds(embed).queue()
        return true
    }

    override fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean {
        val locale = if (context.guild != null) context.locale else context.userLocale

        val restPing = event.jda.restPing.complete()
        val gatewayPing = event.jda.gatewayPing
        val embed = messageService.getBaseEmbed(localeService.getMessage("discord.command.ping.title", locale))
            .setDescription(localeService.getMessage("discord.command.ping.description", locale, restPing, gatewayPing))
            .build()
        event.interaction.replyEmbeds(embed).queue()
        return true
    }
}