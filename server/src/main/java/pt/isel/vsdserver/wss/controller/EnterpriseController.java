package pt.isel.vsdserver.wss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.vsdserver.session.SessionMapService;

@RestController
public class EnterpriseController {

    @Autowired
    private SessionMapService sessionMapService;

    //@MessageMapping("/queue/")
}
