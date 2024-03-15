package net.kingchev.catalyst.ru.worker.commands.misc

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.DiscordLocale
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.kingchev.catalyst.ru.core.model.CatalystLang
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import net.kingchev.catalyst.ru.discord.command.model.AbstractCommand
import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event
import java.util.stream.Collectors


@CatalystCommand(
    key = "setl",
    description = "discord.command.metadata.set.language.description",
    group = "discord.command.group.misc"
)
class SetLocaleCommand : AbstractCommand() {
    override fun build(): SlashCommandData {
        val options = listOf(
            OptionData(
                OptionType.STRING,
                localeService.getMessage("discord.command.metadata.set.language.option.name", LocaleUtils.DEFAULT.language),
                localeService.getMessage("discord.command.metadata.set.language.option.description", LocaleUtils.DEFAULT.language),
                true,
                true
            )
                .setNameLocalization(
                    DiscordLocale.RUSSIAN,
                    localeService.getMessage("discord.command.metadata.set.language.option.name", "ru")
                )
                .setDescriptionLocalization(
                    DiscordLocale.RUSSIAN,
                    localeService.getMessage("discord.command.metadata.set.language.option.description", "ru")
            )
        )

        val locales = hashMapOf<DiscordLocale, String>()
        locales[DiscordLocale.RUSSIAN] = localeService.getMessage(getAnnotation().description, "ru")

        val description = localeService.getMessage(getAnnotation().description, LocaleUtils.DEFAULT.language)

        val builder = Commands.slash(
            getAnnotation().key,
            description
        )
            .addOptions(options)
            .setDescriptionLocalizations(locales)

        return builder
    }

    override fun execute(event: MessageReceivedEvent, context: MessageContext): Boolean {
        val lang = try {
            context.args[0]
        } catch (e: IndexOutOfBoundsException) {
            val embed = messageService.getErrorEmbed(
                localeService.getMessage("discord.command.error.title", context.locale),
                localeService.getMessage("discord.command.error.noarg", context.locale, "language")
            )
            event.message.replyEmbeds(embed).queue()
            return false
        }

        if (!LocaleUtils.containsLang(lang)) {
            val embed = messageService.getErrorEmbed(
                localeService.getMessage("discord.command.error.title", context.locale),
                localeService.getMessage("discord.command.error.set.language.notbeset", context.locale, lang)
            )
            event.message.replyEmbeds(embed).queue()
            return false
        }

        val config = guildConfigService.getById(context.guild?.idLong)
        config.locale = lang
        context.locale = lang
        guildConfigService.save(config)
        val embed = messageService.getBaseEmbed(localeService.getMessage("discord.command.set.language.title", context.locale))
            .setDescription(localeService.getMessage("discord.command.set.language.description", context.locale, lang))
            .build()
        event.message.replyEmbeds(embed).queue()
        return true
    }

    override fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean {
        val lang = context.options[0].asString

        if (!LocaleUtils.containsLang(lang)) {
            val embed = messageService.getErrorEmbed(
                localeService.getMessage("discord.command.error.title", context.locale),
                localeService.getMessage("discord.command.error.set.language.notbeset", context.locale, lang)
            )
            event.interaction.replyEmbeds(embed)
            return false
        }

        val config = guildConfigService.getById(context.guild?.idLong)
        config.locale = lang
        context.locale = lang
        guildConfigService.save(config)
        val embed = messageService.getBaseEmbed(localeService.getMessage("discord.command.set.language.title", context.locale))
            .setDescription(localeService.getMessage("discord.command.set.language.description", context.locale, lang))
            .build()
        event.interaction.replyEmbeds(embed).queue()
        return true
    }

    @CatalystEvent("setLanguageAutoComplete")
    class SetLanguageAutoComplete : ListenerAdapter(), Event {
        override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {
            if (event.fullCommandName == "setl" && event.focusedOption.name == "language") {
                val options = CatalystLang.entries.stream()
                    .filter { it.language.startsWith(event.focusedOption.value) }
                    .map { Command.Choice(
                        "${it.englishName} (${it.nativeName})",
                        it.language) }
                    .collect(Collectors.toList())
                event.replyChoices(options).queue()
            }
        }
    }
}