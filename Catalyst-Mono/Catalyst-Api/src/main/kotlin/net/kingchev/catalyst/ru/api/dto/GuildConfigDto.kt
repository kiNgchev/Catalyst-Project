package net.kingchev.catalyst.ru.api.dto

import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import java.io.Serializable

data class GuildConfigDto(
    val guildId: Long? = null,
    val locale: String = LocaleUtils.DEFAULT.language
) : Serializable
