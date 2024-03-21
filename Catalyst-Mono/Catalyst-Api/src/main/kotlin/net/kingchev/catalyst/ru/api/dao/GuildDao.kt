package net.kingchev.catalyst.ru.api.dao

import net.kingchev.catalyst.ru.api.dto.GuildConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class GuildDao : AbstractDao() {
    @Autowired
    private lateinit var guildConfigService: GuildConfigService

    @Transactional
    fun getGuildConfig(guildId: Long): Mono<GuildConfigDto> {
        val config = guildConfigService.getById(guildId)
            .map { GuildConfigDto(it.guildId, it.locale) }
        return config
    }

    @Transactional
    fun saveUserConfig(guildConfigDto: GuildConfigDto, userId: Long): Mono<GuildConfig> {
        return guildConfigService.getById(userId)
            .flatMap {
                configMapper.updateGuildConfig(guildConfigDto, it)
                guildConfigService.save(it)
            }
    }
}