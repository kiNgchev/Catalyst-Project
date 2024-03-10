package net.kingchev.catalyst.ru.core.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import net.kingchev.catalyst.ru.core.persistence.entity.base.GuildEntity

@Entity
@Table(name = "guild_config")
data class GuildConfig(
    @Column
    var locale: String? = null
) : GuildEntity()
