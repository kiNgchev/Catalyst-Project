package net.kingchev.catalyst.ru.persistence.repository.base

import net.kingchev.catalyst.ru.persistence.entity.base.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface UserRepository<T : UserEntity> : JpaRepository<T, Long> {
    fun getByUserId(id: Long): T?

    fun getAllByUserId(id: Long): List<T>

    fun existsByUserId(id: Long): Boolean
}