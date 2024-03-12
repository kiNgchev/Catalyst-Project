package net.kingchev.catalyst.ru.discord.context.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User

data class MessageContext(
    val message: Message,
    val guild: Guild?,
    val author: User,
    val authorMember: Member?,
    val args: List<String>,
    val locale: String
)