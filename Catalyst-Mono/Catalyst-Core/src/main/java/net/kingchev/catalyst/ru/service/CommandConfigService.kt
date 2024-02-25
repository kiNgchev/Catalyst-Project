package net.kingchev.catalyst.ru.service

import net.kingchev.catalyst.ru.persistence.entity.config.CommandConfig

interface CommandConfigService {
    fun findAll(guildId: Long): List<CommandConfig>

    fun findByKey(guildId: Long, key: String): CommandConfig?

    fun save(config: CommandConfig): CommandConfig

    fun save(config: Iterable<CommandConfig>): Iterable<CommandConfig>
}