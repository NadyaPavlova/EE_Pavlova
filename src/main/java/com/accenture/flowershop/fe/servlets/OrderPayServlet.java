package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            obs.payOrder(obs.getOrderById(Long.parseLong(req.getParameter("idOrder"))));
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }
        catch (InternalException e){
            req.setAttribute("ErrorPay",e);
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }
        catch (Exception e){
            req.setAttribute("ErrorPay",InternalException.ERROR_ORDER_PAY);
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }
    }
}
