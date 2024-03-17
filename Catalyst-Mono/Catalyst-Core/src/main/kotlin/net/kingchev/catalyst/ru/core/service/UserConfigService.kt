package net.kingchev.catalyst.ru.core.service

import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig

interface UserConfigService {
    fun getById(id: Long): UserConfig?

    fun getById(id: Long, createNew: Boolean): UserConfig

    fun save(config: UserConfig): UserConfig

    fun delete(config: UserConfig)
}