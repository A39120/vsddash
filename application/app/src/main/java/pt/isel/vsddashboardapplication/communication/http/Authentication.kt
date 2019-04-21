package pt.isel.vsddashboardapplication.communication.http

import pt.isel.vsddashboardapplication.communication.http.model.Session
import pt.isel.vsddashboardapplication.communication.http.model.SessionAuthentication


interface Authentication {

    fun authenticate(details: SessionAuthentication,
                     onSuccessAction: (Session) -> Unit,
                     onErrorAction: () -> Throwable)

    fun getSession() : Session
}