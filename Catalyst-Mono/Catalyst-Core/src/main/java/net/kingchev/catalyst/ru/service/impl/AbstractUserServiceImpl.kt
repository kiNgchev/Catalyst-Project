package net.kingchev.catalyst.ru.service.impl

import lombok.extern.slf4j.Slf4j
import net.dv8tion.jda.api.entities.User
import net.kingchev.catalyst.ru.persistence.entity.base.UserEntity
import net.kingchev.catalyst.ru.persistence.repository.base.UserRepository
import net.kingchev.catalyst.ru.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.ApplicationContext
import org.springframework.transaction.annotation.Transactional

@Slf4j
@EnableCaching
abstract class AbstractUserServiceImpl<T : UserEntity, R : UserRepository<T>> protected constructor(
    protected val repository: R,
) : UserService<T> {
    private val `$lock`: Any = arrayOfNulls<Any>(0)

    @Autowired
    protected lateinit var applicationContext: ApplicationContext

    @Transactional(readOnly = true)
    override fun get(id: Long): T {
        return repository.findById(id).orElse(null)
    }

    @Transactional(readOnly = true)
    override fun get(user: User): T? {
        return getByUserId(user.idLong)
    }

    @Transactional(readOnly = true)
    override fun getByUserId(id: Long): T? {
        return repository.getByUserId(id)
    }

    @Transactional
    override fun save(entity: T): T {
        val result: T = repository.save(entity)
        return result
    }

    @Transactional(readOnly = true)
    override fun exists(id: Long): Boolean {
        return repository.existsByUserId(id)
    }

    @Transactional
    override fun getOrCreate(id: Long): T {
        var result: T? = repository.getByUserId(id)
        if (result == null) {
            synchronized(`$lock`) {
                result = repository.getByUserId(id)
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
