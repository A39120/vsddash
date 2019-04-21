package pt.isel.vsddashboardapplication.communication.http

import pt.isel.vsddashboardapplication.communication.http.model.Session
import pt.isel.vsddashboardapplication.communication.http.model.SessionAuthentication
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import android.os.AsyncTask.execute





class AuthenticationImpl : Authentication {

    override fun authenticate(
        baseUri: String,
        details: SessionAuthentication,
        onSuccessAction: (Session) -> Unit,
        onErrorAction: () -> Throwable
    ) {
        val details = details.getSessionUrlEncodedBody()

        val request = Request.Builder()
            .url("$baseUri/authenticate")
            .post(details)
            .build()

        val response = client.newCall(request).execute()

    }

    override fun getSession(): Session {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}