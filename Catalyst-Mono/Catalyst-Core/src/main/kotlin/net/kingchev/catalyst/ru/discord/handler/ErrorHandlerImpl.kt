package net.kingchev.catalyst.ru.discord.handler

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.core.model.CommandStatus
import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
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

    override fun handleError(event: MessageReceivedEvent, error: CommandStatus) {
        when(error) {
            CommandStatus.SUCCESS -> { }
            CommandStatus.UNKNOWN_ERROR -> { }
            CommandStatus.DM_ONLY_ERROR -> handleDmOnlyError(event)
            CommandStatus.GUILD_ONLY_ERROR -> handleGuildOnlyError(event)
            CommandStatus.BOT_PERMISSIONS_ERROR -> handleBotPermissionsError(event)
            CommandStatus.USER_PERMISSIONS_ERROR -> handleUserPermissionsError(event)
        }
    }

    override fun handleError(event: SlashCommandInteractionEvent, error: CommandStatus) {
        when(error) {
            CommandStatus.SUCCESS -> { }
            CommandStatus.UNKNOWN_ERROR -> { }
            CommandStatus.DM_ONLY_ERROR -> handleDmOnlyError(event)
            CommandStatus.GUILD_ONLY_ERROR -> handleGuildOnlyError(event)
            CommandStatus.BOT_PERMISSIONS_ERROR -> handleBotPermissionsError(event)
            CommandStatus.USER_PERMISSIONS_ERROR -> handleUserPermissionsError(event)
        }
    }

    private fun handleDmOnlyError(event: MessageReceivedEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.author.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.dm.only",
            locale
        )
        event.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleGuildOnlyError(event: MessageReceivedEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.author.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.guild.only",
            locale
        )
        event.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleBotPermissionsError(event: MessageReceivedEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.author.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.bot.permissions",
            locale
        )
        event.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleUserPermissionsError(event: MessageReceivedEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.author.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.user.permissions",
            locale
        )
        event.message.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleDmOnlyError(event: SlashCommandInteractionEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild?.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.user.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.dm.only",
            locale
        )
        event.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleGuildOnlyError(event: SlashCommandInteractionEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild?.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.user.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.guild.only",
            locale
        )
        event.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleBotPermissionsError(event: SlashCommandInteractionEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild?.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.user.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.bot.permissions",
            locale
        )
        event.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }

    private fun handleUserPermissionsError(event: SlashCommandInteractionEvent) {
        val locale: String = try {
            guildConfigService.getById(event.guild?.idLong).locale!!
        } catch (_: IllegalStateException) {
            userConfigService.getById(event.user.idLong).locale!!
        }
        
        val title = localeService.getMessage(
            "discord.command.error.title",
            locale
        )
        val description = localeService.getMessage(
            "discord.command.error.user.permissions",
            locale
        )
        event.interaction.replyEmbeds(messageService.getErrorEmbed(title, description)).queue()
    }
}