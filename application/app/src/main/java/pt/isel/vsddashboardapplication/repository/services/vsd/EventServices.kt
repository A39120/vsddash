package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.events.Events
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface EventServices {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/events")
    fun getEvent(@Query(value = "uuid") uuid: String?) : Deferred<Events?>

}
