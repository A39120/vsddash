package pt.isel.vsdserver.nuage.api;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.nuagenetworks.vspk.v5_0.VSDSession;

/**
 * Model class representing a Nuage user;
 * Serves only to build a session;
 */
@JsonSerialize
public final class NuageUser {

    //@Id

    private String password;
    private String username;
    private String enterprise;
    private String apiUrl;
    private String certificateContent;
    private String privateKeyContent;

    public NuageUser(String username,
                     String enterprise,
                     String apiUrl,
                     String certificateContent,
                     String privateKeyContent) {

        this.username = username;
        this.enterprise = enterprise;
        this.apiUrl = apiUrl;
        this.certificateContent = certificateContent;
        this.privateKeyContent = privateKeyContent;
    }


    public NuageUser(String username,
                     String enterprise,
                     String apiUrl,
                     String password) {

        this.username = username;
        this.enterprise = enterprise;
        this.apiUrl = apiUrl;
        this.password = password;

    }

    //@ManyToOne
    //private final List<Enterprise> enterprises;

    //@ManyToOne
    //private final List<NuageServer> servers;


    public NuageUser(String user) {
        this.username = user;
        //this.enterprises = new ArrayList<>();
        //this.servers = new ArrayList<>();
    }

    public VSDSession createSession(){
        if(password == null)
            return new VSDSession(username, enterprise, apiUrl, certificateContent, privateKeyContent);

        return new VSDSession(username, password, enterprise, apiUrl);
    }

}
