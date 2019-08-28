package com.accenture.flowershop.fe.ws.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController2 {
    UserController2(){
        System.out.println("HAHAHAHAHAHAHAH");
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printHello(){
        return "HELLO WORLD FROM MVC";
    }
}
