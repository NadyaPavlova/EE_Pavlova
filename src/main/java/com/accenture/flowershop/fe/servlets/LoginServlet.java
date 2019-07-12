package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessServiceImpl;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "loginServlet",urlPatterns = "/user/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService ubs;

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter pw = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        User currentUser;
        if((currentUser = ubs.login(login, password)) != null) {
            session.setAttribute("user", currentUser);
        }
        else {
            throw new ServletException("You shall not pass!");
        }

        LOG.info("USER "+ session.getAttribute("user") + " LOGGED IN.");
        req.setAttribute("firstName", currentUser.getFirstName());
        req.setAttribute("middleName", currentUser.getMiddleName());
        req.setAttribute("lastName", currentUser.getLastName());
        req.setAttribute("phoneNumber", currentUser.getPhoneNumber());
        req.setAttribute("email", currentUser.getEmail());
        req.setAttribute("money", currentUser.getMoney());
        req.setAttribute("discount", currentUser.getDiscount());
        if(currentUser.isAdmin()==1) {
            req.setAttribute("role", "Admin");
        }
        else{
            req.setAttribute("role", "User");
        }
        req.getRequestDispatcher("/personalAccount.jsp").forward(req, resp);

    }
}