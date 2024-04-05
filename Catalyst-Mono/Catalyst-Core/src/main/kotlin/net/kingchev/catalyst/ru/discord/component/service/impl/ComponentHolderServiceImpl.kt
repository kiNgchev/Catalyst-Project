package net.kingchev.catalyst.ru.discord.component.service.impl

import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.command.service.impl.CommandHolderServiceImpl.Companion.log
import net.kingchev.catalyst.ru.discord.component.model.IButton
import net.kingchev.catalyst.ru.discord.component.model.IComponent
import net.kingchev.catalyst.ru.discord.component.model.IModal
import net.kingchev.catalyst.ru.discord.component.model.ISelect
import net.kingchev.catalyst.ru.discord.component.service.ComponentHolderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Stream

@Service
class ComponentHolderServiceImpl : ComponentHolderService {
    private val buttons: HashMap<String, IButton> = hashMapOf()
    private val modals: HashMap<String, IModal> = hashMapOf()
    private val selects: HashMap<String, ISelect> = hashMapOf()

    @Autowired
    override fun register(components: Array<IComponent>) {
        Stream.of(*components)
            .parallel()
            .forEach {
                try {
                    when(it) {
                        is IButton -> {
                            buttons[it.getAnnotation().id] = it
                            log.info("Button with id `${it.getAnnotation().id}` is registered")
                        }
                        is IModal -> {
                            modals[it.getAnnotation().id] = it
                            log.info("Modal with id `${it.getAnnotation().id}` is registered")
                        }
                        is ISelect -> {
                            selects[it.getAnnotation().id] = it
                            log.info("Select with id `${it.getAnnotation().id}` is registered")
                        }
                    }
                } catch (_: NullPointerException) {
                    return@forEach
                }
            }
    }

    override fun getButton(id: String): IButton {
        return buttons[id] ?: throw IllegalArgumentException("Button with key `$id` does not exists")
    }

    override fun getModal(id: String): IModal {
        return modals[id] ?: throw IllegalArgumentException("Modals with key `$id` does not exists")
    }

    override fun getSelect(id: String): ISelect {
        return selects[id] ?: throw IllegalArgumentException("Select with key `$id` does not exists")
    }

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger(this::class.java)
    }
}