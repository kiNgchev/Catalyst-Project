package net.kingchev.catalyst.ru.persistence.entity.config

import jakarta.persistence.*
import net.kingchev.catalyst.ru.model.CoolDownMode
import net.kingchev.catalyst.ru.persistence.entity.base.GuildEntity

@Entity
@Table("command_config")
data class CommandConfig(
    @Column
    var key: String? = null,

    @Column
    var disabled: Boolean = false,

    @Column(name = "delete_source")
    var deleteSource: Boolean = false,

    @Column(name = "cooldown")
    var coolDown: Int = 0,

    @Column(name = "cooldown_mode")
    @Enumerated(EnumType.STRING)
    var coolDownMode: CoolDownMode = CoolDownMode.NONE
) : GuildEntity()
