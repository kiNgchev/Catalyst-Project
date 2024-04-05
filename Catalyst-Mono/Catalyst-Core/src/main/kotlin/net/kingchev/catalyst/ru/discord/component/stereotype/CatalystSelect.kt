package net.kingchev.catalyst.ru.discord.component.stereotype

import org.springframework.stereotype.Component
import java.lang.annotation.Inherited

@Inherited
@Component
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS])
annotation class CatalystSelect(
    val id: String
)
