package net.kingchev.catalyst.ru.worker.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.kingchev.catalyst.ru.discord.command.model.AbstractCommand
import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.context.model.MessageContext
import net.kingchev.catalyst.ru.discord.context.model.SlashContext

@CatalystCommand(
    key = "ban",
    description = "Ping",
    aliases = ["хуй", "ban", "ХУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУУЙ"],
    group = "hui",
    dmOnly = true
)
class BanCommand : AbstractCommand() {
    override fun build(): SlashCommandData {
        val builder = Commands.slash(getAnnotation().key, getAnnotation().description)
        return builder
    }

    override fun execute(event: MessageReceivedEvent, context: MessageContext): Boolean {
        println("${context.args}")
        return true
    }

    override fun execute(event: SlashCommandInteractionEvent, context: SlashContext): Boolean {
        println("ХУЙ")
        return true
    }
}