package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.OperationalState

class OperationalStateConverter : BaseMoshiAdapter<OperationalState> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<OperationalState?>): OperationalState? {
        val value = if (jsonReader.peek() == JsonReader.Token.NULL)
            jsonReader.nextNull()
        else
            jsonReader.nextString()

        return OperationalState
            .values()
            .find { it.name == value }
    }

    @TypeConverter
    override fun convertFrom(value: String?) : OperationalState? =
        OperationalState.values().find { it.toString() == value }
}
