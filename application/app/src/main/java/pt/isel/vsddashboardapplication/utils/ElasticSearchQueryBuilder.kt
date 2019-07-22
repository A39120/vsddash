package pt.isel.vsddashboardapplication.utils

import java.lang.StringBuilder
import java.util.*

class ElasticSearchQueryBuilder {

    private val queries = LinkedList<String>()

    fun addRange(field: String, start: Long? = null, end: Long? = null) : ElasticSearchQueryBuilder{
        if(start == null && end == null) {
            addSimpleQuery(field)
            return this
        }

        queries.add("$field:[${start?:'*'} TO ${end?:'*'}]")
        return this
    }

    fun addNullQuery(field: String) : ElasticSearchQueryBuilder {
        queries.add("NOT(_exists_:$field)")
        return this
    }

    fun addSimpleQuery(field: String, value: String? = null) : ElasticSearchQueryBuilder{
        queries.add(field + value?.let { ":$it" })
        return this
    }

    fun build() : String {
        val query = queries.joinToString(separator = " AND ")
        return query
    }

}