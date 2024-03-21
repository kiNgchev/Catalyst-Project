package net.kingchev.catalyst.ru.api.service

import net.kingchev.catalyst.ru.api.dto.GuildConfigDto
import net.kingchev.catalyst.ru.api.dto.UserConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ConfigMapperService {

    @Mappings(
        Mapping(target = "guildId", ignore = true)
    )
    fun updateGuildConfig(config: GuildConfigDto, @MappingTarget guildConfig: GuildConfig)

    @Mappings(
        Mapping(target = "userId", ignore = true)
    )
    fun updateUserConfig(config: UserConfigDto, @MappingTarget userConfig: UserConfig)
}