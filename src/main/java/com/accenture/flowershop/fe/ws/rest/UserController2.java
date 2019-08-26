package com.accenture.flowershop.fe.ws.rest;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/user")
public class UserController2 {
    @Autowired
    UserBusinessService ubs;
    @Autowired
    DozerBeanMapper mapper;
    @Produces("application/json")
    @RequestMapping("/{id}")
    public UserDTO getEmployee(@PathVariable Long id) {
        UserDTO userDTO = mapper.map(ubs.getById(id), UserDTO.class);
        return userDTO;
    }
}
