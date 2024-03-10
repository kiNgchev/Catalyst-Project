package net.kingchev.catalyst.ru.core.persistence.entity.base

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class GuildEntity : BaseEntity() {
    @Column(name = "guild_id")
    var guildId: Long? = null
}