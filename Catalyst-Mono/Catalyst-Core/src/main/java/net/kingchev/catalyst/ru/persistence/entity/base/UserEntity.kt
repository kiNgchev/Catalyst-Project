package net.kingchev.catalyst.ru.persistence.entity.base

import jakarta.persistence.Entity
import jakarta.persistence.MappedSuperclass
import java.io.Serializable

@Entity
@MappedSuperclass
abstract class UserEntity(
    var userId: Long? = null
) : BaseEntity(), Serializable