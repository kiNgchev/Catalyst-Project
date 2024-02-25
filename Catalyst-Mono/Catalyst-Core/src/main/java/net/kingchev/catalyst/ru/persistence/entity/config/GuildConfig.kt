package net.kingchev.catalyst.ru.persistence.entity.config

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import net.kingchev.catalyst.ru.persistence.entity.base.GuildEntity

@Entity
@Table(name = "guild_config")
data class GuildConfig(
    @Basic
    @Size(max = 100)
    var name: String? = null,

    @Basic
    @Size(max = 7)
    var color: String? = null,

    @Column(name = "icon_url")
    var iconUrl: String? = null,

    @Basic
    @NotEmpty
    @Size(max = 20)
    var prefix: String? = null,

    @Column(name = "is_help_private")
    var privateHelp: Boolean? = null,

    @Basic
    @NotEmpty
    @Size(max = 10)
    var locale: String? = null,

    @Basic
    var timezone: String? = null
) : GuildEntity()
