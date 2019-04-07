package pt.isel.vsdserver.session;


import net.nuagenetworks.vspk.v5_0.VSDSession;

public class SessionContainer {

    private long tokenExpiration;
    private VSDSession session;

    public SessionContainer(VSDSession session, long tokenExpiration){
        this.session = session;
        this.tokenExpiration = tokenExpiration;
    }

    public long getTokenExpiration() {
        return tokenExpiration;
    }

    public VSDSession getSession() {
        return session;
    }
}
