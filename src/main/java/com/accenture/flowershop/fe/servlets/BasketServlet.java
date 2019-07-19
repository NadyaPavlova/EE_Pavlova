package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.Mapped;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.BasketDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderItemDTO;
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


@WebServlet(urlPatterns = "/user/BasketServlet")
public class BasketServlet extends HttpServlet {


    @Autowired
    FlowerBusinessService fbs;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flowerId = Long.parseLong(req.getParameter("idFlower"));
        Mapped mp= new Mapped();
        FlowerDTO flowerDTO = new FlowerDTO();
        mp.mapped(fbs.getFlowerById(flowerId),flowerDTO);
        HttpSession session = req.getSession(false);
        BasketDTO basket = (BasketDTO) session.getAttribute("basket");
        addBasket(basket, flowerDTO);
        stockAvailability(basket, session);
        session.setAttribute("basket", basket);
        req.setAttribute("flowers", fbs.getAllFlowers());
        req.getRequestDispatcher("/personalAccount.jsp").forward(req, resp);
        }

    private void stockAvailability(BasketDTO basket,HttpSession session) {
        for (OrderItemDTO fl : basket.getBasketList()) {
            if (fl.getQtyFlower() > fbs.getFlowerById(fl.getIdFlower()).getQtyStock()) {
                session.setAttribute("count", "This number of flowers is not in stock");
                return;
            }
        }
        session.setAttribute("count", "");
    }


    public void addBasket(BasketDTO basket,FlowerDTO flower) {
        Mapped mp= new Mapped();
        for (OrderItemDTO fl : basket.getBasketList()) {
            if (fl.getIdFlower() == flower.getIdFlower()) {
                fl.setQtyFlower((fl.getQtyFlower() + 1));
                return;
            }
        }
        OrderItemDTO orderItem = new OrderItemDTO();
        mp.mapped(flower, orderItem);
        basket.getBasketList().add(orderItem);
    }
    }
