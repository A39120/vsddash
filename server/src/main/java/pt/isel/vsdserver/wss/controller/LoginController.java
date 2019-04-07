package pt.isel.vsdserver.wss.controller;


import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.vsdserver.nuage.api.NuageUser;
import pt.isel.vsdserver.session.SessionMapService;
import pt.isel.vsdserver.session.SessionNotFoundException;
import pt.isel.vsdserver.wss.messages.DefaultMessage;

import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    SessionMapService sessions;


    @MessageMapping
    @SendTo("/topic/login")
    public String LoginSucess(String s){
        return "Success";
    }


    @MessageMapping
    @SendToUser("/queue/enterprise")
    public String message(){ return ""; }

    @MessageMapping("/Message")
    @SendToUser("/queue/reply")
    public String processMessageFromClient(
            @Payload String message,
            Principal principal) throws Exception {
        //return gson
        //        .fromJson(Message, Map.class)
        //        .get("name").toString();
        return "";
    }

    @MessageMapping("/enterprise")
    @SendToUser("/queue/enterprise")
    public String getEnterprise(Principal principal){
        return null;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

    @MessageMapping
    @SendToUser("/queue/login")
    public String loginToVsd(@Payload NuageUser credentials, Principal principal) throws RestException {
        VSDSession session = null;
        String name = principal.getName();

        if(sessions.containsSession(principal.getName())) {
            try {
                session = sessions.getSession(name);
            } catch(SessionNotFoundException e){ }
        }

        if(session == null)
            session = credentials.createSession();

        try {
            sessions.addSession(principal.getName(), session);

        } catch (RestException e){
            return DefaultMessage.FAILURE;
        }

        return DefaultMessage.SUCCESS;
    }

    //@MessageMapping
    //@SendTo("/topic/enterprise")
    //public String getEnterprise(String userKey, String enterprise){ }



}
