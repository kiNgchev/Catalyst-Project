package net.kingchev.catalyst.ru.command.model

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User

class MemberReference {
    var id: String? = null
    var user: User? = null
    var member: Member? = null
    private var authorSelected: Boolean = false
}
