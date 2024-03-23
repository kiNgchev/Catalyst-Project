package net.kingchev.catalyst.ru.core.service

import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import reactor.core.publisher.Mono

interface UserConfigService {
    fun getById(id: Long): Mono<UserConfig>

    fun getById(id: Long?): Mono<UserConfig>

    fun save(config: UserConfig): Mono<UserConfig>

    fun delete(config: UserConfig)
}