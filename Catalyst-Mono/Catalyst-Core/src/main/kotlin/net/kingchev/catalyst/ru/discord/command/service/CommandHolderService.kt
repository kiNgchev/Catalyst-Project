package net.kingchev.catalyst.ru.discord.command.service

import net.kingchev.catalyst.ru.discord.command.model.Command

interface CommandHolderService {
    fun register(commands: Array<Command>)

    fun getCommands(): HashMap<String, Command>

    fun getCommandByKey(key: String): Command
}