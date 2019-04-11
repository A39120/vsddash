package pt.isel.vsdserver.wss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.vsdserver.security.session.SessionMapService;

@RestController
public class EnterpriseController {

    @Autowired
    private SessionMapService sessionMapService;

    //@MessageMapping("/queue/")
}
