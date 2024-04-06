package net.kingchev.catalyst.ru.core.localezied.service

interface LocaleService {
    fun getMessage(key: String, locale: String): String

    fun getMessage(key: String, locale: String, vararg objects: Any): String
}