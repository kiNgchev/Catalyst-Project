package net.kingchev.catalyst.ru.core.persistence.repository

import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.persistence.repository.base.GuildRepository
import org.springframework.data.redis.core.RedisHash
import org.springframework.stereotype.Repository

@RedisHash
@Repository
interface GuildConfigRepository : GuildRepository<GuildConfig> {
}