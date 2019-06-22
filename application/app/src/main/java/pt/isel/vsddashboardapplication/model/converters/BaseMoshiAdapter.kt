package pt.isel.vsddashboardapplication.model.converters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader

interface BaseMoshiAdapter<T> {

    @FromJson fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<T>) : T?

}