package pt.isel.vsdserver.nuage.api;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing a Nuage user;
 *
 */
@Entity
public class NuageUser {

    @Id
    private final String username;

    //@ManyToOne
    //private final List<Enterprise> enterprises;

    //@ManyToOne
    //private final List<NuageServer> servers;


    public NuageUser(String user) {
        this.username = user;
        //this.enterprises = new ArrayList<>();
        //this.servers = new ArrayList<>();
    }



}
