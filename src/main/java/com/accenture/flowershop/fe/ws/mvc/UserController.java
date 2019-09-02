package com.accenture.flowershop.fe.ws.mvc;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserBusinessService userBusinessService;

    @Autowired
    DozerBeanMapper mapper;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public UserController(){
        System.out.println("=== UserController создан ===");
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(){
        HttpSession session = SessionFactory.getSession(false);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        return userDTO;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById (@PathVariable("id") Long id) {
        return userBusinessService.getById(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@QueryParam("login") String login, @QueryParam("password") String password){
        try {
            HttpSession session = SessionFactory.getSession(true);
            UserDTO userDTO = mapper.map(userBusinessService.login(login, password), UserDTO.class);
            session.setAttribute("user", userDTO);
        }
        catch (InternalException e){
            return e.getMessage();
        }
        return "You logged in";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registration(@RequestBody @NotNull User user){
        try {
            userBusinessService.registration(user.getEmail(), user.getPassword(), user.getLastName(), user.getFirstName(), user.getMiddleName(), user.getPhoneNumber());
            return "You are registered";
        }
        catch (InternalException e){
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    void exit(){
        HttpSession session = SessionFactory.getSession(false);
        LOG.info("Пользователь "+ ((UserDTO)session.getAttribute("user")).getLogin()+" вышел из системы");
        session.invalidate();
    }

}
