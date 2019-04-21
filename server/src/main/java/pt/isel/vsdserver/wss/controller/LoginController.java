package pt.isel.vsdserver.wss.controller;


import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Controller;
import pt.isel.vsdserver.security.session.SessionNotFoundException;
import pt.isel.vsdserver.security.session.datastructures.UserMapper;
import pt.isel.vsdserver.utils.HeaderUtils;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserMapper userMapper;

    @Autowired
    public LoginController(UserMapper userMapper){
        this.userMapper = userMapper;
    }


    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        logger.error(exception.getMessage());
        return exception.getMessage();
    }

    @MessageMapping("/login")
    @SendToUser("/queue/login")
    public String login(
            @Payload String message,
            @Header("authorization") String authenticationHeader
    ) throws SessionNotFoundException, RestException {

        Pair<String, String> pair = HeaderUtils.getAuthorizationHeader(authenticationHeader);

        logger.debug("Logging user: " + pair.getFirst());

        VSDSession session = userMapper.getUserWithToken(pair.getFirst(), pair.getSecond());
        session.start();

        return StompCommand.CONNECTED;
    }


}
