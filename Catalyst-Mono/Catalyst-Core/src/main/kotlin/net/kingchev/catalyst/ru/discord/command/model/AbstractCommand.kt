package net.kingchev.catalyst.ru.discord.command.model

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.discord.command.service.InternalCommandService
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.message.service.MessageService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractCommand : Command {
    @Autowired
    protected lateinit var internalCommandService: InternalCommandService

    @Autowired
    protected lateinit var localeService: LocaleService

    @Autowired
    protected lateinit var messageService: MessageService

    open override fun isAvailable(event: MessageReceivedEvent, context: MessageContext): CommandStatus {
        val annotation = getAnnotation()
        val message = context.message
        val user = context.author
        val guild = context.guild
        val member = context.authorMember

        if (guild != null && annotation.dmOnly) {
            return internalCommandService.fail(event, CommandStatus.GUILD_ONLY_ERROR)
        }
        if (guild == null && annotation.guildOnly) {
            return internalCommandService.fail(event, CommandStatus.GUILD_ONLY_ERROR)
        }
        if (guild != null && !guild.selfMember.hasPermission(annotation.permissions.toList())) {
            return internalCommandService.fail(event, CommandStatus.GUILD_ONLY_ERROR)
        }
        if (member != null && !context.authorMember.hasPermission(message.channel.asGuildMessageChannel(), annotation.userPermission.toList())) {
            return internalCommandService.fail(event, CommandStatus.GUILD_ONLY_ERROR)
        }
        return internalCommandService.ok(event, CommandStatus.SUCCESS)
    }
}
