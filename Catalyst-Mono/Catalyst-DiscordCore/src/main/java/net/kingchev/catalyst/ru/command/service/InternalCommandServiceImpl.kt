package net.kingchev.catalyst.ru.command.service

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.command.model.Command
import net.kingchev.catalyst.ru.message.service.MessageService
import net.kingchev.catalyst.ru.persistence.entity.config.CommandConfig
import net.kingchev.catalyst.ru.service.CommandConfigService
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service

@Order(0)
@Service
class InternalCommandServiceImpl : InternalCommandService {
    @Autowired
    private lateinit var holderService: CommandHolderService

    @Autowired
    private lateinit var messageService: MessageService

    @Autowired
    private lateinit var commandConfigService: CommandConfigService

    override fun isValidKey(event: MessageReceivedEvent, key: String): Boolean {
        return holderService.isAnyCommand(key)
    }

    override fun resultEmotion(message: MessageReceivedEvent, emoji: String, messageCode: String?, vararg args: Any) {
        try {
            if (message.guild.selfMember.hasPermission(
                    message.channel.asGuildMessageChannel(),
                    Permission.MESSAGE_ADD_REACTION
                )
            ) {
                try {
                    message.message.addReaction(Emoji.fromUnicode(emoji)).queue()
                    return
                } catch (e: Exception) {
                    // fall down and add emoticon as message
                }
            }
            var text = emoji
            if (StringUtils.isNotEmpty(messageCode) && messageService.hasMessage(messageCode)) {
                text = messageService.getMessage(messageCode, args)
            }
            messageService.sendMessageSilent(message.channel::sendMessage, text)
        } catch (e: Exception) {
            log.error("Add emotion error", e)
        }
    }

    fun isApplicable(
        command: Command,
        commandConfig: CommandConfig,
        user: User,
        member: Member,
        channel: TextChannel
    ): Boolean {
        if (command.getAnnotation() == null) {
            return false
        }
        if (commandConfig.disabled) {
            return false
        }
        val guild = member.guild
        return command.isAvailable(user, guild, member)
    }

    companion object {
        val log = LoggerFactory.getLogger(InternalCommandService::class.java)
    }
}