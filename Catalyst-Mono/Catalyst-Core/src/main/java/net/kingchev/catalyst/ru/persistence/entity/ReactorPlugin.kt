package net.kingchev.catalyst.ru.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import net.kingchev.catalyst.ru.model.Plugin
import net.kingchev.catalyst.ru.persistence.entity.base.BaseEntity

@Entity
@Table(name = "reactor_plugin")
data class ReactorPlugin(
    @Column
    val name: String? = null,

    @Column
    val description: String? = null,

    @Column(name = "plugin")
    val plugin: Plugin? = null,

    @Column
    val rating: Float? = null
) : BaseEntity()
