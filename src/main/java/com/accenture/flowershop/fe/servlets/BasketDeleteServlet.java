package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.Mapper;
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
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/user/BasketDeleteServlet")
public class BasketDeleteServlet extends HttpServlet {
    @Autowired
    private FlowerBusinessService fbs;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flowerId = Long.parseLong(req.getParameter("idFlower"));
        FlowerDTO flowerDTO= Mapper.mapper(fbs.getFlowerById(flowerId));
        HttpSession session = req.getSession(false);
        BasketDTO basket = (BasketDTO)session.getAttribute("basket");
        deleteFlowerBasket(basket, flowerDTO);

        //высчитываем сумму покупки всех товаров в корзине
        priceBasket(basket);

        //проверяем количество на складе и в корзине
        stockAvailability(basket, session);

        session.setAttribute("basket",basket);
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

    private void deleteFlowerBasket(BasketDTO basket,FlowerDTO flower){
        for (OrderItemDTO order : basket.getBasketList()) {
            if (order.getIdFlower() == flower.getIdFlower()) {
                order.setQtyFlower(order.getQtyFlower() - 1);
                order.setPriceFlower(flower.getPrice().multiply(new BigDecimal(order.getQtyFlower())));
            }
            if (order.getQtyFlower() == 0) {
                basket.getBasketList().remove(order);
                return;
            }
        }
    }

    private void priceBasket(BasketDTO basket){

        basket.setPriceSum(new BigDecimal(0));
        for (OrderItemDTO order:basket.getBasketList()) {
            basket.setPriceSum(basket.getPriceSum().add(order.getPriceFlower()));
        }
    }
}
