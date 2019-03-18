package pt.isel.vsdserver.nuage.api;


public class NuageServer {

    private final String ipDomain;
    private final int port;


    public NuageServer(String ipDomain, int port) {
        this.ipDomain = ipDomain;
        this.port = port;
    }

    public String getIpDomain() {
        return ipDomain;
    }

    public int getPort() {
        return port;
    }

    public String getAddress(){
        return "https://" + ipDomain + ":" + port;
    }
}
