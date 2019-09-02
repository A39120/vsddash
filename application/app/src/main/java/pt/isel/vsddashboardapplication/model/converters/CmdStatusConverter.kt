package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus
import pt.isel.vsddashboardapplication.model.enumerables.CmdStatus

class CmdStatusConverter :
    BaseMoshiAdapter<CmdStatus> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<CmdStatus?>): CmdStatus? {
        val value = if (jsonReader.peek() == JsonReader.Token.NULL)
            jsonReader.nextNull()
        else
            jsonReader.nextString()

        return if(value != null)
            CmdStatus
                .values()
                .find { it.name == value }
        else null
    }

    @TypeConverter
    override fun convertFrom(value: String?) : CmdStatus? =
        CmdStatus.values().find { it.toString() == value }

}
