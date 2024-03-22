package net.kingchev.catalyst.ru.api.config

import net.kingchev.catalyst.ru.core.config.CoreConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@Import(
    CoreConfiguration::class
)
@ComponentScan(basePackages = ["net.kingchev.catalyst.ru.api"])
class ApiConfiguration {
}