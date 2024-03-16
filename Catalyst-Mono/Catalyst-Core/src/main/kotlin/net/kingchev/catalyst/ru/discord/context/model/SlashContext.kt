package net.kingchev.catalyst.ru.discord.context.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.interactions.Interaction
import net.dv8tion.jda.api.interactions.commands.Command.Option
import net.dv8tion.jda.api.interactions.commands.OptionMapping

data class SlashContext(
    val interaction: Interaction,
    val guild: Guild?,
    val author: User,
    val authorMember: Member?,
    val options: List<OptionMapping> = listOf(),
    var locale: String,
    var userLocale: String
)