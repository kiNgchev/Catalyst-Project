package net.kingchev.catalyst.ru.api.dto

import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import java.io.Serializable

data class UserConfigDto(
    var userId: Long? = null,
    var locale: String = LocaleUtils.DEFAULT.language
) : Serializable
