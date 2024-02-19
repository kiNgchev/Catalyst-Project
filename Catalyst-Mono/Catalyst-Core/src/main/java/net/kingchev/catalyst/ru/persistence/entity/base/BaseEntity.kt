package net.kingchev.catalyst.ru.persistence.entity.base

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.Objects

@Entity
@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Version
    @Column(name = "version")
    var version: Long? = null
) : Serializable {
    override fun toString(): String {
        return "${this.javaClass.name}[id=$id version=$version]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BaseEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = Objects.hash(id)
}