package net.kingchev.catalyst.ru.persistence.repository.config

import net.kingchev.catalyst.ru.persistence.entity.config.CommandConfig
import net.kingchev.catalyst.ru.persistence.repository.base.GuildRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CommandConfigRepository : GuildRepository<CommandConfig> {
    @Query("SELECT c FROM CommandConfig c WHERE c.guildId = :guildId AND c.key = :key")
    fun getByKey(@Param("guildId") guildId: Long, @Param("key") key: String): CommandConfig?
}