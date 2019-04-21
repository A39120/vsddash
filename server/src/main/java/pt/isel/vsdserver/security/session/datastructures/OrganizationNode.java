package pt.isel.vsdserver.security.session.datastructures;

import net.nuagenetworks.vspk.v5_0.VSDSession;

import java.util.LinkedList;
import java.util.List;

public class OrganizationNode {

    ApiNode api;
    private final String organization;
    private final List<UserNode> users;

    OrganizationNode(String organization){
        this.organization = organization;
        this.users = new LinkedList<>();
    }

    UserNode add(String username, VSDSession session, String token, long timeout){
        for (UserNode user : users)
            if(user.equalsUsername(username))
                return user;

        UserNode nuageUser = new UserNode(username, session, token, timeout);
        nuageUser.organization = this;
        this.users.add(nuageUser);
        return nuageUser;
    }


    public void removeUser(String username){
        users.stream()
                .filter((user) -> user.equalsUsername(username) )
                .findFirst()
                .ifPresent(users::remove);
    }

    boolean contains(String username){
        return users.stream().anyMatch(user -> user.equalsUsername(username));
    }

    void remove(String token){
        users.stream()
                .filter(user -> user.equalsToken(token))
                .findAny()
                .ifPresent(users::remove);
    }

    List<UserNode> getUsers(){ return this.users; }

    String getOrganization(){ return this.organization; }
}
