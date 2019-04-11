package pt.isel.vsdserver.security.session;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionMapService {

    private volatile Map<String, SessionContainer> sessions = new HashMap<>();

    @Value("${user.session.timeout}")
    private long timeout;

    /**
     * Adds a session to the session map
     * @param id - session token
     * @param session - session details
     * @throws RestException, problem using the VSD API
     */
    public synchronized void addSession(String id, VSDSession session) throws RestException {
        if(containsSession(id))
            throw new IllegalArgumentException("Can't add the same session twice");

        long currentTime = System.currentTimeMillis() + timeout;
        session.start();

        SessionContainer sessionContainer = new SessionContainer(session, currentTime);
        sessions.put(id, sessionContainer);


    }

    /**
     * Gets the session associated with the token
     * @param token, session token
     * @return the new session
     * @throws SessionNotFoundException, Session isn't stored
     */
    public synchronized VSDSession getSession(String token) throws SessionNotFoundException {

        if (containsSession(token)) {
            SessionContainer session = sessions.get(token);
            return session.getSession();
        }

        throw new SessionNotFoundException();
    }

    /**
     * Checks if the session exists
     * @param id, session token
     * @return true if session exists
     */
    private synchronized boolean containsSession(String id){
        SessionContainer session = sessions.get(id);
        return !(session == null && session.getTokenExpiration() > System.currentTimeMillis());
    }

    /**
     * Removes session given the session token
     * @param sessionId, the session token
     */
    protected synchronized void removeSession(String sessionId){
        SessionContainer session = sessions.get(sessionId);
        if(session != null) {
            sessions.get(sessionId).getSession().reset();
            sessions.remove(sessionId);
        }
    }
}
