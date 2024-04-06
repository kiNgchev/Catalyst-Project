package net.kingchev.catalyst.ru.discord.command.service.impl

import net.dv8tion.jda.api.JDA
import net.kingchev.catalyst.ru.discord.command.model.CatalystCommand
import net.kingchev.catalyst.ru.discord.command.model.Command
import net.kingchev.catalyst.ru.discord.command.service.CommandHolderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope("singleton")
class CommandHolderServiceImpl : CommandHolderService {

    private val commands: HashMap<String, Command> = hashMapOf()
    private val aliases: HashMap<String, String> = hashMapOf()

    @Autowired
    override fun register(commands: Array<Command>) {
        commands.forEach {
            try {
                val annotation = it.javaClass.getAnnotation(CatalystCommand::class.java)

                if (this.commands[annotation.key] != null) {
                    log.error("Command with key `${annotation.key}` already exists!")
                    return@forEach
                }

                this.commands[annotation.key] = it
                log.info("Command `${annotation.key}` successfully loaded!")

                for (alias in annotation.aliases) {
                    if (this.aliases[alias] != null || this.commands[alias] != null) {
                        log.error("Command with alias `$alias` already exists!")
                        continue
                    }

                    this.aliases[alias] = annotation.key
                    log.info("Alias `$alias` for command `${annotation.key}` successfully loaded!")
                }
            } catch (_: NullPointerException) {
                return@forEach
            }
        }
    }

    override fun getCommands(): HashMap<String, Command> {
        return commands
    }

    override fun getCommandByKey(key: String): Command {
        return commands[key] ?: commands[aliases[key]] ?: throw IllegalArgumentException("Command with key `$key` does not exists")
    }

    override fun registerSlashCommand(jda: JDA) {
        commands.values.stream()
            .parallel()
            .map { it.build() }
            .forEach { jda.upsertCommand(it).queue() }
    }

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger(this::class.java)
    }
}