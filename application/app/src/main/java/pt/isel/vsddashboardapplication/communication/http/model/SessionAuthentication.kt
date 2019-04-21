package pt.isel.vsddashboardapplication.communication.http.model

import okhttp3.MultipartBody

data class SessionAuthentication(
    val api: String,
    val organization: String,
    val username: String,
    val password: String
) {
    fun getSessionUrlEncodedBody() =  MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("api", api)
            .addFormDataPart("organization", organization)
            .addFormDataPart("username", username)
            .addFormDataPart("password", password)
            .build()

}
