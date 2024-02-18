package net.kingchev.catalyst.ru.persistence.repository.base

import net.kingchev.catalyst.ru.persistence.entity.base.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface UserRepositories : JpaRepository<UserEntity, Long> {
    fun getByUserId(id: Long): UserEntity

    fun getAllByUserId(id: Long): List<UserEntity>

    fun existsByUserId(id: Long): Boolean
}