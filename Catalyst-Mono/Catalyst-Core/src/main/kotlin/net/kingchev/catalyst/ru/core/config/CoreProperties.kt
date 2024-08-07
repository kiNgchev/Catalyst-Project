package net.kingchev.catalyst.ru.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(scopeName = "singleton")
@PropertySources(
    PropertySource("classpath:/core.yaml"),
)
@ConfigurationProperties("core-properties")
class CoreProperties {
    val tasks = Tasks()

    val colors = Colors()

    class Tasks {
        val corePoolSize = 5
        val maxPoolSize = 5
        val schedulerPoolSize = 10
    }

    class Colors {
        val errorColor: Int = 0xDC143C
        val critical: Int = 0xff0900
        val purple: Int = 0x7567ff
    }
}