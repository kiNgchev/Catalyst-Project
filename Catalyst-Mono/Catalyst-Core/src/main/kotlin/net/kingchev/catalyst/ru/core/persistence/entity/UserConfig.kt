package net.kingchev.catalyst.ru.core.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import net.kingchev.catalyst.ru.core.persistence.entity.base.UserEntity

@Entity
@Table(name = "user_config")
data class UserConfig(
    @Column
    var locale: String? = null
) : UserEntity()