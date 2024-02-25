package net.kingchev.catalyst.ru.service.impl

import lombok.extern.slf4j.Slf4j
import net.dv8tion.jda.api.entities.Guild
import net.kingchev.catalyst.ru.persistence.entity.base.GuildEntity
import net.kingchev.catalyst.ru.persistence.repository.base.GuildRepository
import net.kingchev.catalyst.ru.service.GuildService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.ApplicationContext
import org.springframework.transaction.annotation.Transactional

@Slf4j
@EnableCaching
abstract class AbstractGuildServiceImpl<T : GuildEntity, R : GuildRepository<T>>(
    protected var repository: R
) : GuildService<T> {
    private val `$lock`: Any = arrayOfNulls<Any>(0)

    @Autowired
    protected lateinit var applicationContext: ApplicationContext

    @Transactional(readOnly = true)
    override fun get(id: Long): T {
        return repository.findById(id).orElse(null)
    }

    @Transactional(readOnly = true)
    override fun get(guild: Guild): T? {
        return getByGuildId(guild.idLong)
    }

    @Transactional(readOnly = true)
    override fun getByGuildId(id: Long): T? {
        return repository.getByGuildId(id)
    }

    @Transactional
    override fun save(entity: T): T {
        val result: T = repository.save(entity)
        return result
    }

    @Transactional(readOnly = true)
    override fun exists(id: Long): Boolean {
        return repository.existsByGuildId(id)
    }

    @Transactional
    override fun getOrCreate(id: Long): T {
        var result: T? = repository.getByGuildId(id)
        if (result == null) {
            synchronized(`$lock`) {
                result = repository.getByGuildId(id)
                if (result == null) {
                    result = createNew(id)
                    repository.saveAndFlush(result!!)
                }
            }
        }
        return result!!
    }

    protected abstract fun createNew(guildId: Long): T
}
