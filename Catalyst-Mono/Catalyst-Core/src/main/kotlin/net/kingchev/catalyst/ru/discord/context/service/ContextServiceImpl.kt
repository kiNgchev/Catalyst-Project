package net.kingchev.catalyst.ru.discord.context.service

import net.kingchev.catalyst.ru.discord.context.model.ComponentContext
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class ContextServiceImpl : ContextService {
    private val contextHolder: ConcurrentHashMap<String, ComponentContext> = ConcurrentHashMap()
    override fun getContext(id: String): Optional<ComponentContext> {
        return Optional.ofNullable(contextHolder[id])
    }

    override fun setContext(context: ComponentContext) {
        contextHolder[context.id] = context
    }
}