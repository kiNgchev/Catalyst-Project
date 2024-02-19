package net.kingchev.catalyst.ru.service

import net.dv8tion.jda.api.entities.Guild
import net.kingchev.catalyst.ru.persistence.entity.base.GuildEntity

interface GuildService<T : GuildEntity> {
    fun get(id: Long): T?

    fun get(guild: Guild): T?

    fun getByGuildId(id: Long): T?

    fun save(entity: T): T

    fun exists(id: Long): Boolean

    fun getOrCreate(id: Long): T
}