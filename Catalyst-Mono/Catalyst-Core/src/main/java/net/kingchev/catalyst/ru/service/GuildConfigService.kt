package net.kingchev.catalyst.ru.service

import net.kingchev.catalyst.ru.persistence.entity.config.GuildConfig

interface GuildConfigService : GuildService<GuildConfig> {
    fun getPrefix(guildId: Long): String?

    fun getLocale(guildId: Long): String?

    fun getColor(guildId: Long): String?
}