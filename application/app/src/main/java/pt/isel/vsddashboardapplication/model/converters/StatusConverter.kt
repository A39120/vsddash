package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.Status

class StatusConverter :
    BaseMoshiAdapter<Status> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<Status?>): Status? {
        val value = if (jsonReader.peek() == JsonReader.Token.NULL)
            jsonReader.nextNull()
        else
            jsonReader.nextString()

        val result = if(value != null)
            Status
                .values()
                .find { it.name == value }
        else null
        return result
    }

    @TypeConverter
    override fun convertFrom(value: String?) : Status? =
        Status.values().find { it.toString() == value }

}
