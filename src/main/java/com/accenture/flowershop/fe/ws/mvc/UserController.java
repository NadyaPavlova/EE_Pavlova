package com.accenture.flowershop.fe.ws.mvc;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserBusinessService userBusinessService;

    @Autowired
    DozerBeanMapper mapper;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    UserController(){
        System.out.println("=== UserController создан ===");
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(){
        HttpSession session = SessionFactory.getSession(false);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        return userDTO;
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

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registration(@QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("lastName") String lastName,@QueryParam("firstName") String firstName,@QueryParam("middleName") String middleName,@QueryParam("phoneNumber") String phoneNumber){
        try {
            userBusinessService.registration(email, password, lastName, firstName, middleName, phoneNumber);
        }
        catch (InternalException e){
            return e.getMessage();
        }
        return "You are registered";
    }
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    void exit(){
        HttpSession session = SessionFactory.getSession(false);
        LOG.info("Пользователь "+ ((UserDTO)session.getAttribute("user")).getLogin()+" вышел из системы");
        session.invalidate();
    }

}
