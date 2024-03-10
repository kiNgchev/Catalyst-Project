package net.kingchev.catalyst.ru.worker.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.kingchev.catalyst.ru.discord.command.model.AbstractCommand
import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.context.model.MessageContext

@CatalystCommand(
    key = "ban",
    description = "descr",
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

    override fun execute(event: SlashCommandInteractionEvent): Boolean {
        println("ХУЙ")
        return true
    }
}