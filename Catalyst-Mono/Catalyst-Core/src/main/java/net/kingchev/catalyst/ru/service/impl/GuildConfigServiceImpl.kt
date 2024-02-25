package net.kingchev.catalyst.ru.service.impl

import net.kingchev.catalyst.ru.config.CommonProperties
import net.kingchev.catalyst.ru.persistence.entity.config.GuildConfig
import net.kingchev.catalyst.ru.persistence.repository.config.GuildConfigRepository
import net.kingchev.catalyst.ru.service.GuildConfigService
import net.kingchev.catalyst.ru.utils.LocaleUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.EnableCaching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@EnableCaching
class GuildConfigServiceImpl(
    @Autowired
    repository: GuildConfigRepository
) : AbstractGuildServiceImpl<GuildConfig, GuildConfigRepository>(repository), GuildConfigService {
    @Autowired
    private lateinit var commonProperties: CommonProperties

    @Transactional(readOnly = true)
    override fun getPrefix(guildId: Long): String {
        val prefix: String? = repository.findPrefixByGuildId(guildId)
        return prefix ?: commonProperties.discord.defaultPrefix
    }

    @Transactional(readOnly = true)
    override fun getLocale(guildId: Long): String {
        return repository.findLocaleByGuildId(guildId)
    }

    @Transactional(readOnly = true)
    override fun getColor(guildId: Long): String {
        return repository.findColorByGuildId(guildId) ?: commonProperties.discord.defaultAccentColor
    }

    override fun createNew(guildId: Long): GuildConfig {
        val config = GuildConfig()
        config.guildId = guildId
        config.prefix = commonProperties.discord.defaultPrefix
        config.color = commonProperties.discord.defaultAccentColor
        config.locale = LocaleUtils.DEFAULT_LOCALE
        config.timezone = "Etc/Greenwich"
        return config
    }

    protected fun getDomainClass(): Class<GuildConfig> = GuildConfig::class.java
}