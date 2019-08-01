package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.dto.UserDTO;
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

@WebServlet(urlPatterns = "/user/OrderPayService")
public class OrderPayServlet extends HttpServlet {
    @Autowired
    OrderBusinessService obs;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            UserDTO user = (UserDTO)session.getAttribute("user");
            Order order = obs.getOrderById(Long.parseLong(req.getParameter("idOrder")));
            if(user.getMoney().compareTo(order.getPriceSum())==1) {
                obs.payOrder(obs.getOrderById(Long.parseLong(req.getParameter("idOrder"))));
                req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
            }
            else{
                req.setAttribute("ErrorPay","Заказ не оплачен. Пополните счет.");
                req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
            }
        }
        catch (Exception e){

        }
    }
}
