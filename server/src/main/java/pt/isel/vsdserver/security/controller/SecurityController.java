package pt.isel.vsdserver.security.controller;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pt.isel.vsdserver.security.exception.InvalidUserException;
import pt.isel.vsdserver.security.models.NuageUser;
import pt.isel.vsdserver.security.models.Session;
import pt.isel.vsdserver.security.session.datastructures.UserMapper;
import pt.isel.vsdserver.security.session.datastructures.UserNode;

@RestController
public class SecurityController {

    private final UserMapper userMapper;

    @Value("${user.session.timeout}")
    private long timeout;


    @Autowired
    private SecurityController(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Session login(@RequestParam MultiValueMap<String, String> paramMap) throws InvalidUserException, RestException {

        if(paramMap == null)
            throw new InvalidUserException("Please specify parameters.");

        NuageUser nuageUser = NuageUser.parseFromEncoded(paramMap);
        VSDSession vsdSession = nuageUser.createSession();

        UserNode user =
                userMapper.add(nuageUser.getApiUrl(), nuageUser.getOrganization(), nuageUser.getUsername(), vsdSession);

        return user.getSessionResponse();
    }

    @ExceptionHandler
    public ResponseEntity failedAuthentication(){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Something went wrong with the authentication");
    }

}
