package pt.isel.vsddashboardapplication.model.events
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "events")
data class Events(
    @PrimaryKey @Json(name = "uuid") var uuid: String = "",
    @Ignore @Json(name = "events") val events: List<Event?> = listOf(),
    var timestamp: Long? = System.currentTimeMillis()
)