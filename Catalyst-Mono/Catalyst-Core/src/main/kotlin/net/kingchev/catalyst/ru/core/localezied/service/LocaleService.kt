package net.kingchev.catalyst.ru.core.localezied.service

import java.util.Locale

interface LocaleService {
    fun getMessage(key: String, locale: String): String

    fun getMessage(key: String, locale: String, vararg objects: Any): String
}