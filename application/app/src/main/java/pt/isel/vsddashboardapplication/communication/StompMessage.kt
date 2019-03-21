package pt.isel.vsddashboardapplication.communication

import java.lang.StringBuilder


class StompMessage(val command: String) {
    companion object {

        /**
         * Transforms a String into a StompMessage
         * @param str, the string that has the message
         */
        fun fromString(str: String) : StompMessage{
            val lines = str.split('\n')

            var body = false
            val (headers, strbody) = lines.partition {
                if(body) return@partition false

                body = it.equals("")
                return@partition !body
            }

            val message = StompMessage(headers[0])
            headers.subList(1, headers.lastIndex)
                .forEach {
                    val (hkey, hval) =  it.split(':')
                    message.headers[hkey] = hval
                }

            message.content = strbody.joinToString(separator = "\n")
            return message
        }

    }

    val headers = HashMap<String, String>()
    var content : String? = null

    fun getHeader(name: String): String? = headers[name]

    fun put(name: String, value: String) {
        headers[name] = value
    }

    override fun toString(): String {
        val strbuild = StringBuilder()

        strbuild.append("${this.command}\n")

        headers.forEach{ strbuild.append("${it.key}:${it.value}\n") }

        strbuild.append("\n$content" + 0.toChar())

        return strbuild.toString()
    }

}