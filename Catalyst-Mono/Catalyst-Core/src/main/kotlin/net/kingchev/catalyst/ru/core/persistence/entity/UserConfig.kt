package net.kingchev.catalyst.ru.core.persistence.entity

import net.kingchev.catalyst.ru.core.persistence.entity.base.UserEntity
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "user_config")
data class UserConfig(
    @Column
    var locale: String = LocaleUtils.DEFAULT.language
) : UserEntity()