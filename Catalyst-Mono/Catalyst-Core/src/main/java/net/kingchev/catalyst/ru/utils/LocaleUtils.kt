package net.kingchev.catalyst.ru.utils

import java.util.*

object LocaleUtils {
    const val DEFAULT_LOCALE: String = "en"
    const val RU_LOCALE: String = "ru"

    private var SUPPORTED_LOCALES: Map<String, Locale>

    init {
        val localeMap: MutableMap<String, Locale> = HashMap()
        localeMap[DEFAULT_LOCALE] = Locale.US
        localeMap[RU_LOCALE] = Locale.forLanguageTag("ru-RU")
        SUPPORTED_LOCALES = Collections.unmodifiableMap(localeMap)
    }

    @JvmStatic
    fun get(tag: String): Locale? = SUPPORTED_LOCALES[tag]

    @JvmStatic
    fun getOrDefault(tag: String): Locale? = SUPPORTED_LOCALES.getOrDefault(tag, get(DEFAULT_LOCALE))

    @JvmStatic
    fun isSupported(tag: String): Boolean {
        return SUPPORTED_LOCALES.containsKey(tag)
    }
}