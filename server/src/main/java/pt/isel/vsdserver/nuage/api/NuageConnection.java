package pt.isel.vsdserver.nuage.api;

public class NuageConnection {

    private final String vsdhost, username, organization, cert, key, password;

    public NuageConnection(String vsdhost,
                           String username,
                           String organization,
                           String password)
    {
        this.vsdhost = vsdhost;
        this.username = username;
        this.organization = organization;
        this.password = password;
        this.cert = null;
        this.key = null;
    }

    public NuageConnection(String vsdhost,
                           String username,
                           String organization,
                           String cert,
                           String key)
    {
        this.vsdhost = vsdhost;
        this.username = username;
        this.organization = organization;
        this.cert = cert;
        this.key = key;
        this.password = null;
    }

    public void connect(){

    }
}
