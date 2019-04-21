package pt.isel.vsdserver.security.session.datastructures;

import net.nuagenetworks.vspk.v5_0.VSDSession;

import java.util.LinkedList;
import java.util.List;

public class ApiNode {

    private final String apiUrl;
    private final List<OrganizationNode> organizations;

    ApiNode(String apiUrl){
        this.apiUrl = apiUrl;
        this.organizations = new LinkedList<>();
    }

    boolean contains(String organization, String username){
        boolean[] exists = {false};
        organizations
                .stream()
                .filter((org) -> org.getOrganization().equals(organization))
                .findFirst()
                .ifPresent((org) -> { exists[0] = org.contains(username); });

        return exists[0];
    }

   protected UserNode add(String organization, String username, VSDSession session, String token, long timeout){
        for(OrganizationNode org: organizations){
            if(org.getOrganization().equals(organization)){
                return org.add(username, session, token, timeout);
            }
        }

        OrganizationNode org = new OrganizationNode(organization);
        org.api = this;
        return org.add(username, session, token, timeout);
    }

    void remove(String token){
        for(OrganizationNode org: organizations){
            org.remove(token);
        }
    }


    protected boolean equalsApi(String apiUrl){ return this.apiUrl.equals(apiUrl); }

    protected List<OrganizationNode> getOrganizations() {
        return organizations;
    }
}


