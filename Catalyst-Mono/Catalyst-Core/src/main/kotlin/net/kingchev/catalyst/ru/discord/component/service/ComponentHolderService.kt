package net.kingchev.catalyst.ru.discord.component.service

import net.kingchev.catalyst.ru.discord.component.model.IButton
import net.kingchev.catalyst.ru.discord.component.model.IComponent
import net.kingchev.catalyst.ru.discord.component.model.IModal
import net.kingchev.catalyst.ru.discord.component.model.ISelect

interface ComponentHolderService {
    fun register(components: Array<IComponent>)

    fun getButton(id: String): IButton

    fun getModal(id: String): IModal

    fun getSelect(id: String): ISelect
}