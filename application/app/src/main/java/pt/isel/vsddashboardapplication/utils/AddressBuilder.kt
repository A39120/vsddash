package pt.isel.vsddashboardapplication.utils

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
    }

    fun buildEsAddress(address: String, port: Int = 6200) = build(address, port)

}