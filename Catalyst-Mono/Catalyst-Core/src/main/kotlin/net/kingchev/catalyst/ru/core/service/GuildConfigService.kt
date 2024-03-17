package net.kingchev.catalyst.ru.core.service

import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig

interface GuildConfigService {
    fun getById(id: Long): GuildConfig?

    fun getById(id: Long, createNew: Boolean): GuildConfig

    fun save(config: GuildConfig): GuildConfig

    fun delete(config: GuildConfig)
}