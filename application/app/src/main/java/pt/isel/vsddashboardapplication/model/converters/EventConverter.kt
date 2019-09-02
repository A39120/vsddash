package pt.isel.vsddashboardapplication.model.converters

import com.squareup.moshi.*
import pt.isel.vsddashboardapplication.model.*
import pt.isel.vsddashboardapplication.model.events.Event

class EventConverter {

    @FromJson
    fun fromJson(item: Event): Event? {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Any::class.java)
        val json = item.entities?.mapNotNull { adapter.toJson(it) }

        //val json : List<String> = item.entities?.map{item ->
        //    moshi.adapter(Map::class.java).toJson(item as Map<*, *>)
        //} ?: listOf()

        return Event(
            json,
            item.entityType,
            item.eventReceivedTime,
            item.type,
            item.userName
        )
    }


}