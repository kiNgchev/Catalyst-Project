package net.kingchev.catalyst.ru.core.persistence.repository.base

import net.kingchev.catalyst.ru.core.persistence.entity.base.GuildEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.NoRepositoryBean
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@NoRepositoryBean
interface GuildRepository<T : GuildEntity> : R2dbcRepository<T, Long> {
    fun getByGuildId(id: Long): Mono<T>

    fun getAllByGuildId(id: Long): Flux<T>

    fun existsByGuildId(id: Long): Mono<Boolean>
}