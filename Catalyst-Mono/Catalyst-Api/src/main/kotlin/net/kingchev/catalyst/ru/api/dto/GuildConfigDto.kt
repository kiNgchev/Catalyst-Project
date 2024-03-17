package net.kingchev.catalyst.ru.api.dto

import java.io.Serializable

data class GuildConfigDto(
    val guildId: Long? = null,
    val locale: String? = null
) : Serializable
