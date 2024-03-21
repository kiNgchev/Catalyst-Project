package net.kingchev.catalyst.ru.core.persistence.entity.base

import org.springframework.data.annotation.Id
import java.io.Serializable
import java.util.Objects

abstract class BaseEntity : Serializable {
    @Id
    var id: Long? = null

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        val obj: BaseEntity = other as BaseEntity
        return Objects.equals(id, obj.id)
    }
}