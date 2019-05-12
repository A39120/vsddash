package pt.isel.vsdashbapi.vsdashapi.communication.http.client

object NuageHeaders {


    /**
     * Request only headers
     */
    const val ORGANIZATION = "X-Nuage-Organization"

    /**
     * Request and response headers
     */
    const val PAGE = "X-Nuage-Page"
    const val FILTER = "X-Nuage-Filter"
    const val ORDERBY = "X-Nuage-OrderBy"

    /**
     * Response only Headers
     */
    const val COUNT = "X-Nuage-Count"
    const val PAGESIZE = "X-Nuage-PageSize"
    const val FILTERTYPE = "X-Nuage-FilterType"
}