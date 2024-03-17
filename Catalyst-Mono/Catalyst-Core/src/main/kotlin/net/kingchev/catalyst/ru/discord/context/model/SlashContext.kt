package net.kingchev.catalyst.ru.discord.context.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.interactions.commands.OptionMapping
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction

data class SlashContext(
    val interaction: SlashCommandInteraction,
    val guild: Guild?,
    val author: User,
    val authorMember: Member?,
    val options: List<OptionMapping> = listOf(),
    var guildLocale: String,
    var userLocale: String
) {
    fun setLocale(locale: String) {
        if (guild != null) {
            this.guildLocale = locale
        } else {
            this.userLocale = locale
        }
    }

    fun getLocale(): String {
        return if (guild != null) guildLocale else userLocale
    }
}