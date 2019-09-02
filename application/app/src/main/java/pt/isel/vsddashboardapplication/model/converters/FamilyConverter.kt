package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.Family

class FamilyConverter :
    BaseMoshiAdapter<Family> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<Family?>): Family? {
        val value = if (jsonReader.peek() == JsonReader.Token.NULL)
            jsonReader.nextNull()
        else
            jsonReader.nextString()

        return if(value != null)
            Family
                .values()
                .find { it.name == value }
        else null
    }

    @TypeConverter
    override fun convertFrom(value: String?) : Family? =
        Family.values().find { it.toString() == value }
}
