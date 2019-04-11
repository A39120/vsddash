package pt.isel.vsdserver.security.controller;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pt.isel.vsdserver.security.TokenHasher;
import pt.isel.vsdserver.security.exception.InvalidUserException;
import pt.isel.vsdserver.security.models.NuageUser;
import pt.isel.vsdserver.security.models.Session;
import pt.isel.vsdserver.security.session.SessionMapService;

@RestController
public class SecurityController {

    private TokenHasher hasher;
    private SessionMapService sessions;

    @Value("${user.session.timeout}")
    private long timeout;


    @Autowired
    private SecurityController(SessionMapService sessions, TokenHasher hasher){
        this.sessions = sessions;
        this.hasher = hasher;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Session login(MultiValueMap paramMap) throws InvalidUserException, RestException {

        NuageUser nuageUser = NuageUser.parseFromEncoded(paramMap);
        VSDSession vsdSession = nuageUser.createSession();
        String token = nuageUser.getToken(hasher);
        sessions.addSession(token, vsdSession);

        return new Session(token, System.currentTimeMillis() + timeout);
    }

}
