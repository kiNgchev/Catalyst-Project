package net.kingchev.catalyst.ru.core.utils

import java.util.*

object LocaleUtils {
    @JvmStatic
    val LANGS: HashMap<String, Locale> = hashMapOf("en" to CatalystLang.EN_US.locale)

    val DEFAULT = LANGS["en"]

    init {
        for (lang in CatalystLang.entries) {
            LANGS[lang.language] = lang.locale
        }
    }

    fun get(locale: String): Locale = LANGS[locale] ?: DEFAULT!!
}

enum class CatalystLang(
    val language: String,
    val country: String,
    val nativeName: String,
    val englishName: String,
    vararg val other: String
) {
    EN_US("en", "US", "English", "English"),
    RU_RU("ru", "RU", "Русский", "Russian", "cyka blyat", "cheeki breeki", "rush b");

    val locale: Locale = Locale(language, country)
    val code: String = language + "_" + country

    fun parse(input: String): CatalystLang {
        CatalystLang.entries.forEach {
            if (it.name.equals(input, ignoreCase = true)
                || it.code.equals(input, ignoreCase = true)
                || it.nativeName.equals(input, ignoreCase = true)
                || it.englishName.equals(input, ignoreCase = true)
                || it.other.contains(input)
            ) return it
        }

        return EN_US
    }
}
