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

@CatalystCommand(
    key = "ping",
    description = "discord.command.metadata.ping.description",
    aliases = ["pong", "pi"],
    group = "discord.command.group.misc",
    guildOnly = false
)
class PingCommand : AbstractCommand() {
    private val getRestPingEmoji = { ping: Long -> when {
        ping < 200 -> ":green_circle:"
        ping in 200..400 -> ":yellow_circle:"
        else -> ":red_circle:"
    }}

    private val getGatewayPingEmoji = { ping: Long -> when {
        ping < 100 -> ":green_circle:"
        ping in 100..200 -> ":yellow_circle:"
        else -> ":red_circle:"
    }}

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
        val locale = context.getLocale()

        val restPing = event.jda.restPing.complete()
        val gatewayPing = event.jda.gatewayPing
        val restPingEmoji = getRestPingEmoji(restPing)
        val gatewayPingEmoji = getGatewayPingEmoji(gatewayPing)

        val description = StringBuilder()
            .append(localeService.getMessage("discord.command.ping.description.current.shard", locale, event.jda.shardInfo.shardId))
            .append("\n\n")
            .append(localeService.getMessage(
                "discord.command.ping.description",
                locale,
                restPingEmoji,
                restPing,
                gatewayPingEmoji,
                gatewayPing))
            .append("\n\n")
            .append(localeService.getMessage("discord.command.ping.description.other.shards", locale))
            .append("\n")

        val shards = event.jda.shardManager?.shards

        shards?.shuffled()?.parallelStream()
            ?.limit(10)
            ?.forEach {
                if (it.shardInfo.shardId != event.jda.shardInfo.shardId) {
                    description.append(
                        localeService.getMessage(
                            "discord.command.ping.description.shard",
                            locale,
                            it.shardInfo.shardId,
                            it.gatewayPing
                        )
                    )
                    description.append("\n")
                }
            }

        val embed = messageService.getBaseEmbed(localeService.getMessage("discord.command.ping.title", locale))
            .setDescription(description)
            .setColor(coreProperties.colors.purple)
            .build()
        event.message.replyEmbeds(embed).queue()
        return true
    }

    override fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean {
        val locale = context.getLocale()

        val hook = event.interaction.deferReply().complete()

        val restPing = event.jda.restPing.complete()
        val gatewayPing = event.jda.gatewayPing
        val restPingEmoji = getRestPingEmoji(restPing)
        val gatewayPingEmoji = getGatewayPingEmoji(gatewayPing)

        val description = StringBuilder()
            .append(localeService.getMessage("discord.command.ping.description.current.shard", locale, event.jda.shardInfo.shardId))
            .append("\n\n")
            .append(localeService.getMessage(
                "discord.command.ping.description",
                locale,
                restPingEmoji,
                restPing,
                gatewayPingEmoji,
                gatewayPing))
            .append("\n\n")
            .append(localeService.getMessage("discord.command.ping.description.other.shards", locale))
            .append("\n")

        val shards = event.jda.shardManager?.shards

        shards?.shuffled()?.parallelStream()
            ?.limit(10)
            ?.forEach {
                if (it.shardInfo.shardId != event.jda.shardInfo.shardId) {
                    description.append(
                        localeService.getMessage(
                            "discord.command.ping.description.shard",
                            locale,
                            it.shardInfo.shardId,
                            it.gatewayPing
                        )
                    )
                    description.append("\n")
                }
            }

        val embed = messageService.getBaseEmbed(localeService.getMessage("discord.command.ping.title", locale))
            .setDescription(description)
            .setColor(coreProperties.colors.purple)
            .build()
        hook.editOriginalEmbeds(embed).queue()
        return true
    }
}