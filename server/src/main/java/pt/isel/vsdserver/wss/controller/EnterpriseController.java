package pt.isel.vsdserver.wss.controller;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.Enterprise;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.vsdserver.security.session.SessionMapService;
import pt.isel.vsdserver.security.session.SessionNotFoundException;

import java.util.List;

@RestController
public class EnterpriseController {

    private SessionMapService sessionMapService;


    @Autowired
    public EnterpriseController(SessionMapService sessions){
        this.sessionMapService = sessions;
    }


    @SendToUser("/queue/reply")
    public List<Enterprise> processMessage(@Header("authorization") String token) throws SessionNotFoundException, RestException {
        VSDSession session = sessionMapService.getSession(token);
        List<Enterprise> enterprises = session.getMe().getEnterprises().get();
        return null;
    }
}
