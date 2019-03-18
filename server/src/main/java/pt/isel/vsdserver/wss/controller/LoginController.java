package pt.isel.vsdserver.wss.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @MessageMapping
    @SendTo("/topic/login")
    public String LoginSucess(String s){
        return "Success";
    }


}
