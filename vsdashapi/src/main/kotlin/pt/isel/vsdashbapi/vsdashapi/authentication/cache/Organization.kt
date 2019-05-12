package pt.isel.vsdashbapi.vsdashapi.authentication.cache

import net.nuagenetworks.vspk.v5_0.Me
import java.util.*

class Organization(val organization: String) {

    internal val users = LinkedList<Me>()

    internal fun addUser(user: Me){
        users.find { it.userName == user.userName }?:
                users.add(user)
    }

    internal fun getUser(apiKey: String) : Me? {
        return users.find { it.apiKey == apiKey }
    }

    internal fun removeUser(apiKey: String) {
        users.remove(getUser(apiKey))
    }
}
