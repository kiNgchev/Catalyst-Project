package net.kingchev.catalyst.ru.core.persistence.entity.base

import org.springframework.data.relational.core.mapping.Column

abstract class UserEntity : BaseEntity() {
    @Column(value = "user_id")
    var userId: Long? = null
}