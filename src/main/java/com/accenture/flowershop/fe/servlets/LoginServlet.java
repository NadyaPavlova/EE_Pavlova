package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "loginServlet", urlPatterns = "/user/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private FlowerBusinessService fbs;
    @Autowired
    private UserBusinessService ubs;
    @Autowired
    private OrderBusinessService obs;
    @Autowired
    DozerBeanMapper mapper;

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        try {
            User currentUser;
            currentUser = ubs.login(login, password);
            UserDTO userDTO = mapper.map(currentUser, UserDTO.class);
            session.setAttribute("user", userDTO);
            LOG.info("Пользователь " + session.getAttribute("user") + " авторизовался в системе.");
            if (currentUser.isAdmin() == 1) {
                session.setAttribute("role", "Admin");
            } else {
                session.setAttribute("role", "User");
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserDTO(userDTO);
            orderDTO.setPriceSum(BigDecimal.ZERO.setScale(2));
            session.setAttribute("basket", orderDTO);
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }
        catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorLoginPassword","Ошибка в логине и/или пароле!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}