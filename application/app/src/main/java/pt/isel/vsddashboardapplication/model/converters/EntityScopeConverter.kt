package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.EntityScope

class EntityScopeConverter : BaseMoshiAdapter<EntityScope> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<EntityScope?>): EntityScope? {
        val value = if (jsonReader.peek() == JsonReader.Token.NULL)
            jsonReader.nextNull()
        else
            jsonReader.nextString()

        return if(value != null)
            EntityScope
                .values()
                .find { it.name == value }
        else null
    }

    @TypeConverter
    override fun convertFrom(value: String?) : EntityScope? =
        EntityScope.values().find { it.toString() == value }

}
