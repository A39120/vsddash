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

    fun build() :String {
        return queries.joinToString(" AND ")
    }

    companion object {

        private const val TIME_BETWEEN : Long = 30 * 1000

        fun calculateSize(from: Long, to: Long) : Int {
            val total = to - from
            //val totalPages =
            return 1000
        }

    }

}