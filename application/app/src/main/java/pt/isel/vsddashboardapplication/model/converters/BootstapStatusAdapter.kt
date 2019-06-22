package pt.isel.vsddashboardapplication.model.converters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus

class BootstapStatusAdapter :
    BaseMoshiAdapter<BootstrapStatus> {

    @FromJson
    override fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<BootstrapStatus>): BootstrapStatus? {
        val value = jsonReader.nextString()

        return BootstrapStatus
            .values()
            .find { it.name == value } }

}