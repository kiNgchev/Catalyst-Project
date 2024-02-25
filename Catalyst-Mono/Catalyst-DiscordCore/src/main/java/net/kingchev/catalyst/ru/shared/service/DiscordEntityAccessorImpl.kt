package net.kingchev.catalyst.ru.shared.service

import net.dv8tion.jda.api.entities.Guild
import net.kingchev.catalyst.ru.persistence.entity.config.GuildConfig
import net.kingchev.catalyst.ru.service.GuildConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DiscordEntityAccessorImpl : DiscordEntityAccessor {

    @Autowired
    private lateinit var configService: GuildConfigService

    @Transactional
    override fun getOrCreate(guild: Guild): GuildConfig {
        val config: GuildConfig = configService.getOrCreate(guild.idLong)
        return updateIfRequired(guild, config)
    }

    private fun updateIfRequired(guild: Guild, config: GuildConfig): GuildConfig {
        try {
            var shouldSave = false
            if (config.name != guild.name) {
                config.name = guild.name
                shouldSave = true
            }
            if (config.iconUrl != guild.iconUrl) {
                config.iconUrl = guild.iconUrl
                shouldSave = true
            }
            if (shouldSave) {
                configService.save(config)
            }
        } catch (e: ObjectOptimisticLockingFailureException) {
            // it's ok to ignore optlock here, anyway it will be updated later
        }
        return config
    }
}
