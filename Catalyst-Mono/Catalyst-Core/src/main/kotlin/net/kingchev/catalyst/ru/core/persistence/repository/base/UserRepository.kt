package net.kingchev.catalyst.ru.core.persistence.repository.base

import net.kingchev.catalyst.ru.core.persistence.entity.base.UserEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.NoRepositoryBean
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@NoRepositoryBean
interface UserRepository<T : UserEntity> : R2dbcRepository<T, Long> {
    fun getByUserId(id: Long): Mono<T>

    fun getAllByUserId(id: Long): Flux<T>

    fun existsByUserId(id: Long): Mono<Boolean>
}