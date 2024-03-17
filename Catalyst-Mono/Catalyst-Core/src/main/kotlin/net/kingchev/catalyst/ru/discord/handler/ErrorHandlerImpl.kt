package net.kingchev.catalyst.ru.discord.handler

import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import net.kingchev.catalyst.ru.discord.message.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ErrorHandlerImpl : ErrorHandler {
    @Autowired
    private lateinit var guildConfigService: GuildConfigService

    @Autowired
    private lateinit var userConfigService: GuildConfigService

    @Autowired
    private lateinit var messageService: MessageService

    @Autowired
    private lateinit var localeService: LocaleService

    override fun handleError(context: MessageContext, error: CommandStatus) {
        when(error) {
            CommandStatus.DM_ONLY_ERROR -> handleDmOnlyError(context)
            CommandStatus.GUILD_ONLY_ERROR -> handleGuildOnlyError(context)
            CommandStatus.BOT_PERMISSIONS_ERROR -> handleBotPermissionsError(context)
            CommandStatus.USER_PERMISSIONS_ERROR -> handleUserPermissionsError(context)
            else -> { }
        }
    }

    override fun handleError(context: SlashContext, error: CommandStatus) {
        when(error) {
            CommandStatus.DM_ONLY_ERROR -> handleDmOnlyError(context)
            CommandStatus.GUILD_ONLY_ERROR -> handleGuildOnlyError(context)
            CommandStatus.BOT_PERMISSIONS_ERROR -> handleBotPermissionsError(context)
            CommandStatus.USER_PERMISSIONS_ERROR -> handleUserPermissionsError(context)
            else -> { }
        }
    }

    private fun handleDmOnlyError(context: MessageContext) {
        val locale = context.getLocale()

        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.dm.only",
            locale
        )
        context.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleGuildOnlyError(context: MessageContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.guild.only",
            locale
        )
        context.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleBotPermissionsError(context: MessageContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.bot.permissions",
            locale
        )
        context.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleUserPermissionsError(context: MessageContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.user.permissions",
            locale
        )
        context.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleDmOnlyError(context: SlashContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.dm.only",
            locale
        )
        context.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleGuildOnlyError(context: SlashContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.guild.only",
            locale
        )
        context.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleBotPermissionsError(context: SlashContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.bot.permissions",
            locale
        )
        context.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleUserPermissionsError(context: SlashContext) {
        val locale = context.getLocale()
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.user.permissions",
            locale
        )
        context.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }
}