package pt.isel.vsdserver.wss.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import pt.isel.vsdserver.security.session.SessionMapService;

import java.security.Principal;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SessionMapService sessions;

    @MessageMapping("/message")
    @SendToUser("/queue/reply")
    public String processMessage(@Payload String message){
        logger.info(message);
        return message;
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
            Principal principal
    ){
        logger.info("Logging user: " + principal.getName());
        return "Hello";
    }

}
