package net.kingchev.catalyst.ru.core.persistence.entity.base

import jakarta.persistence.*
import java.io.Serializable
import java.util.Objects

@MappedSuperclass
abstract class BaseEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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