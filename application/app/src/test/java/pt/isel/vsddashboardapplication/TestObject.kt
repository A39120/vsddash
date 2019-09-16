package pt.isel.vsddashboardapplication

import java.util.*


object TestObject {
    private const val esPort = "6200"
    private const val vsdPort = "8443"
    private const val address = "124.252.253.124"
    const val esApi ="https://$address:$esPort"
    const val api = "https://$address:$vsdPort"
    const val organization = "csp"
    private const val username = "admin"
    private const val password = "QDBcPyGxMha71hj1"

    fun getAuth() = "Basic ${String(getBase64Auth(), Charsets.UTF_8)}"

    private fun getBase64Auth() : ByteArray {
        val r = "$username:$password"
        val s = r.toByteArray()
        return Base64.getEncoder().encode(s)
    }
}