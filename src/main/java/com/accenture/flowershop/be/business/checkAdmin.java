package com.accenture.flowershop.be.business;

import com.accenture.flowershop.fe.dto.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class checkAdmin {
    @Before("@annotation(com.accenture.flowershop.be.business.Secured)&&@annotation(SecuredAnnotation)")
    public Object secure(JoinPoint joinPoint, Secured SecuredAnnotation) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        //System.out.println("This Session Id for current Session: " + session.getId());
        if(userDTO.getAdmin()==1)
            return joinPoint;
        else
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable());
    }

}
