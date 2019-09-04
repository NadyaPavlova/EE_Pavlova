package com.accenture.flowershop.be.business.aspects;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.annotation.SecuredAnnotation;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.ws.mvc.SessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(com.accenture.flowershop.be.business.annotation.SecuredAnnotation) && @annotation(securedAnnotation)")
    public void secure(final JoinPoint jp, SecuredAnnotation securedAnnotation) throws Throwable{
        HttpSession session = SessionFactory.getSession(false);
        if (session == null) {
            throw new InternalException(InternalException.ERROR_SESSION_NULL);
        }
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO.getAdmin() != securedAnnotation.onlyAdmin()){
            throw new InternalException(InternalException.ERROR_ACCESS);
        }

    }
}
