package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.PortType

class PortTypeConverter :
    BaseMoshiAdapter<PortType> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<PortType?>): PortType? {
        val value = if (jsonReader.peek() == JsonReader.Token.NULL)
            jsonReader.nextNull()
        else
            jsonReader.nextString()

        return PortType
                .values()
                .find { it.name == value }
        //else null
    }

    @TypeConverter
    override fun convertFrom(value: String?) : PortType? =
        PortType.values().find { it.toString() == value }

}
