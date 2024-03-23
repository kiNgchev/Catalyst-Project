package net.kingchev.catalyst.ru.core.persistence.entity

import net.kingchev.catalyst.ru.core.persistence.entity.base.GuildEntity
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "guild_config")
data class GuildConfig(
    @Column
    var locale: String = LocaleUtils.DEFAULT.language
) : GuildEntity()
