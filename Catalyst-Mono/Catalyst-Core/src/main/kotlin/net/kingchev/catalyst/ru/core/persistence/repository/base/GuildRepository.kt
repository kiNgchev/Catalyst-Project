package net.kingchev.catalyst.ru.core.persistence.repository.base

import net.kingchev.catalyst.ru.core.persistence.entity.base.GuildEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface GuildRepository<T : GuildEntity> : JpaRepository<T, Long> {
    fun getByGuildId(id: Long): T?

    fun getAllByGuildId(id: Long): List<T>

    fun existsByGuildId(id: Long): Boolean
}