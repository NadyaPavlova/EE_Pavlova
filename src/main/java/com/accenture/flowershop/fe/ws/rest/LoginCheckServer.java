package com.accenture.flowershop.fe.ws.rest;


import com.accenture.flowershop.be.business.annotation.SecuredAnnotation;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("/login")
public class LoginCheckServer {

    @Autowired
    UserBusinessService ubs;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/{name}")
    @SecuredAnnotation
    public boolean checkUserList(@PathParam("name") String username) {
        try {
            if(ubs.getUserByLogin(username) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}