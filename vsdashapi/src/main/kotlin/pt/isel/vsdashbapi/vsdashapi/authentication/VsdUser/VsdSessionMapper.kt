package pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser

import net.nuagenetworks.vspk.v5_0.VSDSession

object VsdSessionMapper {

    private val mapper = HashMap<String, VSDSession>()

    @Synchronized
    fun getSession(api: String) =
        mapper[api]

    @Synchronized
    fun putSession(api: String, session: VSDSession) {
        if(mapper[api] == null)
            mapper[api] = session
    }
}