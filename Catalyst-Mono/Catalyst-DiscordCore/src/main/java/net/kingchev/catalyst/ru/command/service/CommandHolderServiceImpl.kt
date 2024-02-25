package net.kingchev.catalyst.ru.command.service

import lombok.Getter
import net.kingchev.catalyst.ru.command.model.CatalystCommand
import net.kingchev.catalyst.ru.command.model.Command
import net.kingchev.catalyst.ru.utils.LocaleUtils
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate
import java.util.stream.Collectors

@Service
class CommandHolderServiceImpl : CommandHolderService {

    @Getter
    private var publicCommandKeys: MutableSet<String> = mutableSetOf()

    private var reverseCommandKeys: MutableSet<String?>? = mutableSetOf()

    @Getter
    private var commands: MutableMap<String, Command> = mutableMapOf()

    @Getter
    private var publicCommands: MutableMap<String, Command> = mutableMapOf()

    override fun getCommands(): Map<String, Command> {
        return commands;
    }

    override fun isAnyCommand(key: String): Boolean {
        val reverseKey = StringUtils.reverse(key).lowercase(Locale.getDefault())
        return (CollectionUtils.isNotEmpty(reverseCommandKeys)
                && reverseCommandKeys!!.stream().anyMatch { prefix: String? ->
            reverseKey.startsWith(
                prefix!!
            )
        })
    }

    @Autowired
    private fun registerCommands(commands: List<Command>) {
        this.commands = HashMap<String, Command>()
        this.publicCommands = HashMap<String, Command>()
        this.publicCommandKeys = HashSet()
        this.reverseCommandKeys = HashSet()
        val locales = LocaleUtils.SUPPORTED_LOCALES.values
        commands.stream().filter(Predicate<Command> { e: Command ->
            e.javaClass.isAnnotationPresent(
                CatalystCommand::class.java
            )
        }).forEach(Consumer<Command> { e: Command ->
            val annotation: CatalystCommand = e.javaClass.getAnnotation(CatalystCommand::class.java)
            val rawKey: String = annotation.key
            this.commands[rawKey] = e
            if (!annotation.hidden) {
                publicCommands[rawKey] = e
            }
        })

        this.commands = Collections.unmodifiableMap(this.commands!!)
        this.publicCommands = Collections.unmodifiableMap(this.publicCommands!!)
        this.publicCommandKeys = Collections.unmodifiableSet(this.publicCommandKeys!!)
        this.reverseCommandKeys = Collections.unmodifiableSet(this.reverseCommandKeys!!)
    }
}