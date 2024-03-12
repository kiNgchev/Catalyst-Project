package net.kingchev.catalyst.ru.discord.command.handle

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import net.kingchev.catalyst.ru.discord.command.model.Command
import net.kingchev.catalyst.ru.discord.command.service.CommandHolderService
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event
import org.springframework.beans.factory.annotation.Autowired

@CatalystEvent(eventName = "CommandHandlerEvent")
class CommandHandler : ListenerAdapter(), Event {
    @Autowired
    private lateinit var commandHolder: CommandHolderService

    @Autowired
    protected lateinit var guildConfigService: GuildConfigService

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot || event.isWebhookMessage) return

        val message = event.message
        val prefix = "c."

        if (prefix.length == event.message.contentRaw.length) return
        if (!message.contentRaw.startsWith(prefix)) return

        val args = event.message.contentRaw.substring(prefix.length).split(" +".toRegex()).toMutableList()
        val commandKey = args.removeAt(0).lowercase()

        val cmd: Command
        try {
            cmd = commandHolder.getCommandByKey(commandKey)
        } catch (error: IllegalArgumentException) {
            return
        }

        var guild: Guild? = null
        try {
            guild = message.guild
        } catch (_: IllegalStateException) {}

        val context = MessageContext(
            message = message,
            guild = guild,
            author = message.author,
            authorMember = message.member,
            args = args,
            locale = guildConfigService.getById(guild?.idLong).locale ?: LocaleUtils.DEFAULT!!.language
        )

        if (cmd.isAvailable(event, context) == CommandStatus.SUCCESS) {
            cmd.execute(event, context)
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val cmd: Command
        try {
            cmd = commandHolder.getCommandByKey(event.fullCommandName)
        } catch (error: IllegalArgumentException) {
            return
        }

        cmd.execute(event)
    }
}