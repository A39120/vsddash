package pt.isel.vsddashboardapplication.repository.pojo.converters

import androidx.room.TypeConverter
import pt.isel.vsddashboardapplication.repository.pojo.enumerables.BootstrapStatus

class BootstapStatusConverter {

    @TypeConverter
    fun convertTo(value: BootstrapStatus) : String =
        value.toString()

    @TypeConverter
    fun convertFrom(value: String) : BootstrapStatus? =
        BootstrapStatus.values().find { it.name == value }

}