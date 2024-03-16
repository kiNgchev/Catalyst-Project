package net.kingchev.catalyst.ru.core.model

enum class CommandStatus {
    SUCCESS,
    UNKNOWN_ERROR,
    DM_ONLY_ERROR,
    GUILD_ONLY_ERROR,
    BOT_PERMISSIONS_ERROR,
    USER_PERMISSIONS_ERROR
}