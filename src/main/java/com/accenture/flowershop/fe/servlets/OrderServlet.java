package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
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


@WebServlet(urlPatterns = "/user/OrderService")
public class OrderServlet extends HttpServlet {

    @Autowired
    OrderBusinessService obs;

    @Autowired
    FlowerBusinessService fbs;

    @Autowired
    DozerBeanMapper mapper;
    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("basket");
        //Перевод в сущность и запись в БД
        try{
            obs.addOrder(mapper.map(orderDTO, Order.class));
        }
        catch (InternalException e){
            req.setAttribute("ErrorOrder",e.getMessage());
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }
        //очистка корзины
        orderDTO = new OrderDTO();
        orderDTO.setPriceSum(BigDecimal.ZERO);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        orderDTO.setUserDTO(userDTO);
        session.setAttribute("basket", orderDTO);

        req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
    }

}
