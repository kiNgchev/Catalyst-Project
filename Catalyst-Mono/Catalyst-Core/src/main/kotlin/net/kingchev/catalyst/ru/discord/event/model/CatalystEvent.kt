package net.kingchev.catalyst.ru.discord.event.model

import org.springframework.stereotype.Component
import java.lang.annotation.Inherited

@Component
@Inherited
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS])
annotation class CatalystEvent(val eventName: String)
