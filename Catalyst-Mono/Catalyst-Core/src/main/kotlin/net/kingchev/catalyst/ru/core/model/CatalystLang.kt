package net.kingchev.catalyst.ru.core.model

import java.util.*

enum class CatalystLang(
    val language: String,
    val country: String,
    val nativeName: String,
    val englishName: String,
    val emoji: String,
    vararg val other: String
) {
    EN_US("en", "US", "English", "English", ":flag_gb:"),
    RU_RU("ru", "RU", "Русский", "Russian", ":flag_ru:","cyka blyat", "cheeki breeki", "rush b");

    val locale: Locale = Locale(language, country)
    val code: String = language + "_" + country
}
