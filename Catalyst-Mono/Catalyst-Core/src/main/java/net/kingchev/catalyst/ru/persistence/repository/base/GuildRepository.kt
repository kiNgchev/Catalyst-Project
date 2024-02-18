package net.kingchev.catalyst.ru.persistence.repository.base

import net.kingchev.catalyst.ru.persistence.entity.base.GuildEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface GuildRepository : JpaRepository<GuildEntity, Long> {
    fun getByGuildId(id: Long): GuildEntity

    fun getAllByGuildId(id: Long): List<GuildEntity>

    fun existsByGuildId(id: Long): Boolean
}