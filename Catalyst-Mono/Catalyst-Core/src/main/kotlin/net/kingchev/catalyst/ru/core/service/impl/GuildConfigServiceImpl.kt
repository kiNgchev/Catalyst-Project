package net.kingchev.catalyst.ru.core.service.impl

import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.persistence.repository.GuildConfigRepository
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@CacheConfig(cacheNames = ["guild_config_cache"])
class GuildConfigServiceImpl : GuildConfigService {
    @Autowired
    private lateinit var repository: GuildConfigRepository

    @Transactional
    @CachePut(cacheNames = ["guild_config"], key = "#id")
    override fun getById(id: Long): Mono<GuildConfig> {
        return repository.getByGuildId(id).switchIfEmpty(createNew(id))
    }

    @Transactional
    @CachePut(cacheNames = ["guild_config"], key = "#id")
    override fun getById(id: Long?): Mono<GuildConfig> {
        return getById(id ?: -1)
    }

    @Transactional
    @CacheEvict(cacheNames = ["guild_config"])
    override fun save(config: GuildConfig): Mono<GuildConfig> {
        return repository.save(config)
    }

    @Transactional
    @CacheEvict(cacheNames = ["guild_config"])
    override fun delete(config: GuildConfig) {
        return repository.delete(config)
            .cast(Unit::class.java)
            .block()!!
    }

    private fun createNew(guildId: Long): Mono<GuildConfig> {
        val config = GuildConfig()
        config.guildId = guildId
        config.locale = LocaleUtils.DEFAULT.language
        return repository.save(config)
    }
}