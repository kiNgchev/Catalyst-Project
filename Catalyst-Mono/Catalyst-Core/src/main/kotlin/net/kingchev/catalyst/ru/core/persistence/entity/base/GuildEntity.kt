package net.kingchev.catalyst.ru.core.persistence.entity.base

import org.springframework.data.relational.core.mapping.Column

abstract class GuildEntity : BaseEntity() {
    @Column(value = "guild_id")
    var guildId: Long? = null
}