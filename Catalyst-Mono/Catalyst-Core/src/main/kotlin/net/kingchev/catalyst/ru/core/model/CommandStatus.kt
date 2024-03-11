package net.kingchev.catalyst.ru.core.model

enum class CommandStatus {
    SUCCESS,
    DM_ONLY_ERROR,
    GUILD_ONLY_ERROR,
    BOT_PERMISSIONS_ERROR,
    USER_PERMISSIONS_ERROR
}