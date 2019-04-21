package pt.isel.vsdserver.security.session;


import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.bambou.RestFetcher;
import net.nuagenetworks.vspk.v5_0.VSDSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionContainer {

    private long tokenExpiration;
    private VSDSession session;

    private boolean isConnected;


    private final Map<String, FetcherCache> subscribedInformation = new HashMap<>();


    public SessionContainer(VSDSession session, long tokenExpiration){
        this.session = session;
        this.tokenExpiration = tokenExpiration;
        this.isConnected = false;
    }

    public long getTokenExpiration() {
        return tokenExpiration;
    }

    public VSDSession getSession() {
        return session;
    }

    public void setConnected(boolean connected) throws RestException {
        this.session.start();
        this.isConnected = connected;
    }

    public List<?> getInformation(String fetcherName, RestFetcher<?> fetcher) throws RestException {
        FetcherCache<?> cache;

        if((cache = subscribedInformation.get(fetcherName)) == null)
            cache = new FetcherCache(fetcher);

        return cache.getInformation();
    }

    public boolean getConnected(){ return this.isConnected; }

}
