package net.kingchev.catalyst.ru.persistence.repository.config

import net.kingchev.catalyst.ru.persistence.entity.config.GuildConfig
import net.kingchev.catalyst.ru.persistence.repository.base.GuildRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GuildConfigRepository : GuildRepository<GuildConfig> {
    @Query("SELECT e.prefix FROM GuildConfig e WHERE e.guildId = :guildId")
    fun findPrefixByGuildId(@Param("guildId") guildId: Long): String?

    @Query("SELECT e.locale FROM GuildConfig e WHERE e.guildId = :guildId")
    fun findLocaleByGuildId(@Param("guildId") guildId: Long): String

    @Query("SELECT e.color FROM GuildConfig e WHERE e.guildId = :guildId")
    fun findColorByGuildId(@Param("guildId") guildId: Long): String?
}