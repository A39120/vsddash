package pt.isel.vsdashbapi.vsdashapi.authentication.cache

import net.nuagenetworks.vspk.v5_0.Me
import java.util.*

class VsdApi(val api: String) {

    internal val organizations = LinkedList<Organization>()

    fun addUser(organization: String, user: Me){
        val org = organizations
                .find { it.organization == organization }

        if(org != null)
            org.addUser(user)
        else {
            val newOrg = Organization(organization)
            newOrg.addUser(user)
            organizations.add(newOrg)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is VsdApi && this.api == other.api
    }


}