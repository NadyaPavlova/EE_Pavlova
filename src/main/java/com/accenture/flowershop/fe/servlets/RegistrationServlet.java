package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {


    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    @Autowired
    private UserBusinessService ubs;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");

            String lastName = req.getParameter("lastName");
            String firstName = req.getParameter("firstName");
            String middleName = req.getParameter("middleName");
            String phoneNumber = req.getParameter("phoneNumber");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            ubs.registration(email, password, lastName, firstName, middleName, phoneNumber);
            LOG.info("USER "+email+" CREATED.");
            resp.sendRedirect("login.jsp");}
        catch (IOException e){
            new ServletException("You shall not registration!");

        }
    }
}
