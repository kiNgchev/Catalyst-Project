package net.kingchev.catalyst.ru.worker.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import net.kingchev.catalyst.ru.discord.command.model.AbstractCommand
import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.component.model.IButton
import net.kingchev.catalyst.ru.discord.component.stereotype.CatalystButton
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext

@CatalystCommand(
    key = "ban",
    description = "Ping",
    aliases = ["хуй", "ban", "ХУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУЙ"],
    group = "hui",
    dmOnly = true,
    guildOnly = false
)
class BanCommand : AbstractCommand() {
    override fun build(): SlashCommandData {
        val builder = Commands.slash(getAnnotation().key, getAnnotation().description)
        return builder
    }

    override fun execute(event: MessageReceivedEvent, context: MessageContext): Boolean {
        println("${context.args}")
        val message = MessageCreateBuilder()
            .addComponents(ActionRow.of(BanButton().build("ru")))
            .build()
        event.message.channel.sendMessage(message).queue()
        return true
    }

    override fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean {
        println("ХУЙ")
        return true
    }
}

@CatalystButton("BAN")
class BanButton : IButton {
    override fun build(locale: String): Button {
        val button = Button.primary(getAnnotation().id, "BAN")
        return button
    }

    override fun execute(event: ButtonInteractionEvent): Boolean {
        println("ВОУ ВОУ ВОУ ИЗ Э БИГ РЕД ГАН")
        return true
    }

}