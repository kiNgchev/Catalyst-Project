package net.kingchev.catalyst.ru.api.service

import net.kingchev.catalyst.ru.api.dto.GuildConfigDto
import net.kingchev.catalyst.ru.api.dto.UserConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.service.GuildConfigService
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Mapper(componentModel = "spring")
interface MapperService {

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "guildId", ignore = true)
    )
    fun updateGuildConfig(config: GuildConfigDto, @MappingTarget guildConfig: GuildConfig)

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "userId", ignore = true)
    )
    fun updateUserConfig(config: UserConfigDto, @MappingTarget userConfig: UserConfig)
}