package net.kingchev.catalyst.ru.api.dto

import java.io.Serializable

data class UserConfigDto(
    val userId: Long? = null,
    val locale: String? = null
) : Serializable
