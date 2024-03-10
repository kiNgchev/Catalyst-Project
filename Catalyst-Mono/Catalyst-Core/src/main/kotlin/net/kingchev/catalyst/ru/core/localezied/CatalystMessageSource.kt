package net.kingchev.catalyst.ru.core.localezied

import org.springframework.context.support.ResourceBundleMessageSource

class CatalystMessageSource(vararg baseNames: String) : ResourceBundleMessageSource() {
    init {
        this.addBasenames(*baseNames)
        this.defaultEncoding = "UTF-8"
    }
}