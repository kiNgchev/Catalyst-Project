package net.kingchev.catalyst.ru.core.service

import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import reactor.core.publisher.Mono

interface GuildConfigService {
    fun getById(id: Long): Mono<GuildConfig>

    fun getById(id: Long?): Mono<GuildConfig>

    fun save(config: GuildConfig): Mono<GuildConfig>

    fun delete(config: GuildConfig)
}