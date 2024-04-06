package net.kingchev.catalyst.ru.discord.command.handle

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.core.service.UserConfigService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import net.kingchev.catalyst.ru.discord.command.model.Command
import net.kingchev.catalyst.ru.discord.command.service.CommandHolderService
import net.kingchev.catalyst.ru.discord.command.service.InternalCommandService
import net.kingchev.catalyst.ru.discord.config.WorkerProperties
import net.kingchev.catalyst.ru.discord.context.service.ContextService
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event
import org.springframework.beans.factory.annotation.Autowired

@CatalystEvent(eventName = "CommandHandlerEvent")
class CommandHandler : ListenerAdapter(), Event {
    @Autowired
    private lateinit var workerProperties: WorkerProperties

    @Autowired
    private lateinit var internalCommandService: InternalCommandService

    @Autowired
    private lateinit var commandHolder: CommandHolderService

    @Autowired
    private lateinit var guildConfigService: GuildConfigService

    @Autowired
    private lateinit var userConfigService: UserConfigService

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot || event.isWebhookMessage) return

        val message = event.message
        val prefix = workerProperties.discord.prefix

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

        val guildLocale = guildConfigService
            .getById(guild?.idLong)
            .block()?.locale
            ?: LocaleUtils.DEFAULT.language
        val userLocale = userConfigService
            .getById(message.author.idLong)
            .block()?.locale
            ?: LocaleUtils.DEFAULT.language

        val context = ContextService.createMessageContext(event, guildLocale, userLocale, args)

        if (cmd.isAvailable(event, context) == CommandStatus.SUCCESS) {
            if (cmd.execute(event, context))
                internalCommandService.ok(context, CommandStatus.SUCCESS)
            else internalCommandService.fail(context, CommandStatus.UNKNOWN_ERROR)
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val interaction = event.interaction

        val cmd: Command
        try {
            cmd = commandHolder.getCommandByKey(event.fullCommandName)
        } catch (error: IllegalArgumentException) {
            return
        }

        val guildLocale = guildConfigService
            .getById(interaction.guild?.idLong)
            .block()?.locale
            ?: LocaleUtils.DEFAULT.language
        val userLocale = userConfigService
            .getById(event.user.idLong)
            .block()?.locale
            ?: LocaleUtils.DEFAULT.language

        val context = ContextService.createSlashContext(event, guildLocale, userLocale)

        if (cmd.isAvailable(event, context) == CommandStatus.SUCCESS) {
            if (cmd.execute(event, context))
                internalCommandService.ok(context, CommandStatus.SUCCESS)
            else internalCommandService.fail(context, CommandStatus.UNKNOWN_ERROR)
        }
    }
}