package pt.isel.vsddashboardapplication.utils

import java.net.URI

/**
 * Builds address
 */
class AddressBuilder() {

    fun build(address: String, port: Int) : String {
        val uri = URI("$address:$port")
        return uri.toASCIIString()
    }

}