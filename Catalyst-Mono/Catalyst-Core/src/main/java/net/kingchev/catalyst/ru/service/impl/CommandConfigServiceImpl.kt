package net.kingchev.catalyst.ru.service.impl

import net.kingchev.catalyst.ru.persistence.entity.config.CommandConfig
import net.kingchev.catalyst.ru.persistence.repository.config.CommandConfigRepository
import net.kingchev.catalyst.ru.service.CommandConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.EnableCaching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@EnableCaching
class CommandConfigServiceImpl : CommandConfigService {
    @Autowired
    private lateinit var repository: CommandConfigRepository

    @Transactional(readOnly = true)
    @CachePut(value = ["command_config"], key = "#guildId")
    override fun findAll(guildId: Long): List<CommandConfig> {
        return repository.getAllByGuildId(guildId)
    }

    @Transactional(readOnly = true)
    @CachePut(value = ["command_config"], key = "#{key + ' ' + guildId}")
    override fun findByKey(guildId: Long, key: String): CommandConfig? {
        return repository.getByKey(guildId, key)
    }

    @Transactional
    @CacheEvict(value = ["command_config"])
    override fun save(config: CommandConfig): CommandConfig {
        return repository.save(config)
    }

    @Transactional
    @CacheEvict(value = ["command_config"])
    override fun save(configs: Iterable<CommandConfig>): Iterable<CommandConfig> {
        return repository.saveAll(configs)
    }
}