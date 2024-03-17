package net.kingchev.catalyst.ru.api.dao

import net.kingchev.catalyst.ru.api.dto.GuildConfigDto
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GuildDao : AbstractDao() {
    @Autowired
    private lateinit var guildConfigService: GuildConfigService

    @Transactional
    fun getGuildConfig(guildId: Long): GuildConfigDto {
        val config = guildConfigService.getById(guildId, true)
        val dto = GuildConfigDto(config.guildId, config.locale)
        return dto
    }

    @Transactional
    fun saveUserConfig(guildConfigDto: GuildConfigDto, userId: Long) {
        val config = guildConfigService.getById(userId, true)
        mapper.updateGuildConfig(guildConfigDto, config)
        guildConfigService.save(config)
    }
}