package pt.isel.vsdserver.security.session.datastructures;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.isel.vsdserver.security.TokenHasher;
import pt.isel.vsdserver.security.models.NuageUser;
import pt.isel.vsdserver.security.session.SessionContainer;
import pt.isel.vsdserver.security.session.SessionNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Contains all the logged in sessions
 */
@Component
public class UserMapper {


    @Value("${user.session.timeout}")
    private long timeout;

    private final List<ApiNode> root;
    private final TokenHasher tokenHasher;

    @Autowired
    public UserMapper(TokenHasher tokenHasher){
        this.root = new LinkedList<>();
        this.tokenHasher = tokenHasher;
    }

    /**
     * Adds a new user to the logged in list
     * @param api, the api the user is using
     * @param organization, the organization the user belongs to
     * @param username, the username of the user
     * @param session, the user session, check if it's connected
     */
    public UserNode add(String api, String organization, String username, VSDSession session){
        ApiNode apinode = this.add(api);

        long userTimeout = System.currentTimeMillis() + timeout;
        String token = tokenHasher.hash(api+organization+username+userTimeout);

        return apinode.add(organization, username, session, token, userTimeout);
    }

    /**
     * Checks if the user is already logged in.
     * @param api, the api url
     * @param organization, the organization the user is logging in to
     * @param username, the username of the user
     * @param session, the user session
     * @return if the user is already logged in
     */
    public boolean contains(String api, String organization, String username, SessionContainer session){
        boolean[] exists = { false };
        root.stream()
                .filter((node) -> node.equalsApi(api))
                .findAny()
                .ifPresent((node) ->  { exists[0] = node.contains(organization, username);});

        return exists[0];
    }

    /**
     * Removes the user from the tree
     * @param token, token used by the user to log in the websocket channel
     */
    private void remove(String token){
        root.forEach(api -> api.remove(token));
    }

    /**
     * Adds an VSD API to the tree
     * @param apiUrl, the url of the VSD api;
     * @return the new API Tree Node
     */
    private ApiNode add(String apiUrl){
        Optional<ApiNode> apinode = root.stream()
                .filter((node) -> node.equalsApi(apiUrl))
                .findAny();

        if(apinode.isEmpty()) {
            apinode = Optional.of(new ApiNode(apiUrl));
            this.root.add(apinode.get());
        }

        return apinode.get();
    }

    /**
     * Gets the session of the token
     * @param token the token used in the current session
     * @return the VSD session
     */
    public VSDSession getUserWithToken(String username, String token) throws SessionNotFoundException {
        UserNode usernode = getUser(username, token);
        return usernode.getSession();
    }

    private UserNode getUser(String username, String token) throws SessionNotFoundException {
        Optional<UserNode> node = this.root.stream()
                .flatMap(api -> api.getOrganizations().stream())
                .flatMap(org -> org.getUsers().stream())
                .filter(user -> user.equalsToken(token) && user.equalsUsername(username))
                .findFirst();

        if(node.isPresent())
            return node.get();

        throw new SessionNotFoundException();

    }

}
