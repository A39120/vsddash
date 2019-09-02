package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader

interface BaseMoshiAdapter<T : Enum<T>> {

    @FromJson fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<T?>) : T?

    @TypeConverter
    fun convertTo(value: T?) : String? = value?.toString()

    @TypeConverter
    fun convertFrom(value: String?) : T?

}