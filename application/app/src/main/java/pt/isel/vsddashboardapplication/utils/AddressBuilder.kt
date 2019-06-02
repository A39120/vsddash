package pt.isel.vsddashboardapplication.utils

import java.lang.StringBuilder
import java.net.URI

/**
 * Builds address
 */
object AddressBuilder {

    fun build(address: String, port: Int) : String {
        val uri = StringBuilder()
        if(!address.startsWith("https://"))
            uri.append("https://")
        uri.append("$address:$port")
        return uri.toString()
        //val uri = URI("$address:$port")
        //return uri.toASCIIString()
    }

}