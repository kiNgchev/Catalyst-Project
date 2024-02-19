package net.kingchev.catalyst.ru.service

import net.dv8tion.jda.api.entities.User
import net.kingchev.catalyst.ru.persistence.entity.base.UserEntity

interface UserService<T : UserEntity> {
    fun get(id: Long): T?

    fun get(user: User): T?

    fun getByUserId(id: Long): T?

    fun save(entity: T): T

    fun exists(id: Long): Boolean

    fun getOrCreate(id: Long): T
}