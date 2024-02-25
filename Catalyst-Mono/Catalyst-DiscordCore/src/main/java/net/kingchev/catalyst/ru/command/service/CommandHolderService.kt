package net.kingchev.catalyst.ru.command.service

import net.kingchev.catalyst.ru.command.model.Command

interface CommandHolderService {
    fun getCommands(): Map<String, Command>

    fun isAnyCommand(key: String): Boolean
}