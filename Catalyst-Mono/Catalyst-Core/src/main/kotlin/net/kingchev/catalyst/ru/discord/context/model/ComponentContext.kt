package net.kingchev.catalyst.ru.discord.context.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.interactions.Interaction

data class ComponentContext(
    val id: String,
    val message: Message? = null,
    val interaction: Interaction? = null,
    val guild: Guild? = null,
    val author: User? = null,
    val guildLocale: String,
    val userLocale: String,
    val objects: List<Any> = listOf()
)