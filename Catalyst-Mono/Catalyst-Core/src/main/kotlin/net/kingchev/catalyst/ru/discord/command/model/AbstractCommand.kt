package net.kingchev.catalyst.ru.discord.command.model

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.kingchev.catalyst.ru.core.config.CoreProperties
import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.core.service.UserConfigService
import net.kingchev.catalyst.ru.discord.command.service.InternalCommandService
import net.kingchev.catalyst.ru.discord.config.WorkerProperties
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import net.kingchev.catalyst.ru.discord.message.service.MessageService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractCommand : Command {
    @Autowired
    protected lateinit var internalCommandService: InternalCommandService

    @Autowired
    protected lateinit var localeService: LocaleService

    @Autowired
    protected lateinit var messageService: MessageService

    @Autowired
    protected lateinit var guildConfigService: GuildConfigService

    @Autowired
    protected lateinit var userConfigService: UserConfigService

    @Autowired
    protected lateinit var coreProperties: CoreProperties

    @Autowired
    protected lateinit var workerProperties: WorkerProperties

    open override fun isAvailable(event: MessageReceivedEvent, context: MessageContext): CommandStatus {
        val annotation = getAnnotation()
        val message = context.message
        val user = context.author
        val guild = context.guild
        val member = context.authorMember

        if (guild != null && annotation.dmOnly) {
            return internalCommandService.fail(context, CommandStatus.DM_ONLY_ERROR)
        }
        if (guild == null && annotation.guildOnly) {
            return internalCommandService.fail(context, CommandStatus.GUILD_ONLY_ERROR)
        }
        if (guild != null && !guild.selfMember.hasPermission(annotation.permissions.toList())) {
            return internalCommandService.fail(context, CommandStatus.BOT_PERMISSIONS_ERROR)
        }
        if (member != null && !context.authorMember.hasPermission(message.channel.asGuildMessageChannel(), annotation.userPermission.toList())) {
            return internalCommandService.fail(context, CommandStatus.USER_PERMISSIONS_ERROR)
        }
        return CommandStatus.SUCCESS
    }

    open override fun isAvailable(event: SlashCommandInteractionEvent, context: SlashContext): CommandStatus {
        val annotation = getAnnotation()
        val message = context.interaction
        val user = context.author
        val guild = context.guild
        val member = context.authorMember

        if (guild != null && annotation.dmOnly) {
            return internalCommandService.fail(context, CommandStatus.DM_ONLY_ERROR)
        }
        if (guild == null && annotation.guildOnly) {
            return internalCommandService.fail(context, CommandStatus.GUILD_ONLY_ERROR)
        }
        if (guild != null && !guild.selfMember.hasPermission(annotation.permissions.toList())) {
            return internalCommandService.fail(context, CommandStatus.BOT_PERMISSIONS_ERROR)
        }
        if (member != null && !context.authorMember.hasPermission(message.guildChannel, annotation.userPermission.toList())) {
            return internalCommandService.fail(context, CommandStatus.USER_PERMISSIONS_ERROR)
        }
        return CommandStatus.SUCCESS
    }
}
