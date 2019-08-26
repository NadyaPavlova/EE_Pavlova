/*
package com.accenture.flowershop.fe.ws.rest;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path(value = "/user")
public class UserController {
    @Autowired
    UserBusinessService ubs;
    @Autowired
    DozerBeanMapper mapper;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @GET
    public UserDTO getEmployee(@PathParam("id") Long id) {
        UserDTO userDTO = mapper.map(ubs.getById(id), UserDTO.class);

        return userDTO;
    }
}*/
