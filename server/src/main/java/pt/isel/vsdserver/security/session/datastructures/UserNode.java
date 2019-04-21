package pt.isel.vsdserver.security.session.datastructures;

import net.nuagenetworks.vspk.v5_0.VSDSession;
import pt.isel.vsdserver.security.models.Session;

public class UserNode {

    protected OrganizationNode organization;

    private final String username;
    private final long timeout;
    private final String token;
    private final VSDSession session;
    private boolean isConnected;

    public UserNode(String username, VSDSession session, String token, long timeout) {
        this.username = username;
        this.token = token;
        this.timeout = timeout;
        this.session = session;
        this.isConnected = false;
    }

    boolean equalsUsername(String username){
        return username.equals(this.username);
    }

    public VSDSession getSession(){
        return this.session;
    }

    public boolean isConnected(){
        return isConnected;
    }

    public boolean equalsToken(String token){
        return this.token.equals(token);
    }

    public Session getSessionResponse(){
        return new Session(token, timeout);
    }
}
