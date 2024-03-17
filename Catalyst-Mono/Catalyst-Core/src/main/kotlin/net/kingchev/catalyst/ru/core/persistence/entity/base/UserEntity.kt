package net.kingchev.catalyst.ru.core.persistence.entity.base

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class UserEntity : BaseEntity() {
    @Column(name = "user_id")
    var userId: Long? = null
}