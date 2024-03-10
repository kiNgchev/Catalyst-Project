package net.kingchev.catalyst.ru.core.service.impl

import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.persistence.repository.GuildConfigRepository
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class GuildConfigServiceImpl : GuildConfigService {
    @Autowired
    private lateinit var repository: GuildConfigRepository

    @CachePut(cacheNames = ["guild_config"], key = "#id")
    override fun getById(id: Long): GuildConfig {
        return repository.getByGuildId(id) ?: createNew(id)
    }

    @CacheEvict(cacheNames = ["guild_config"])
    override fun save(config: GuildConfig): GuildConfig {
        return repository.save(config)
    }

    @CacheEvict(cacheNames = ["guild_config"])
    override fun delete(config: GuildConfig) {
        return repository.delete(config)
    }

    private fun createNew(guildId: Long): GuildConfig {
        val config = GuildConfig()
        config.guildId = guildId
        return repository.save(config)
    }
}