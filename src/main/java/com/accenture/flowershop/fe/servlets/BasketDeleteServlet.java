package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.Mapper;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.OrderItemDTO;
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
        OrderDTO basket = (OrderDTO)session.getAttribute("basket");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        //удаляем цветок из корзины
        deleteFlowerBasket(basket, flowerDTO);

        //считаем сумму корзины
        priceBasket(basket, userDTO.getDiscount());

        session.setAttribute("basket",basket);

        req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
    }


    private void deleteFlowerBasket(OrderDTO basket, FlowerDTO flower){
        for (OrderItemDTO orderItem : basket.getBasketList()) {
            if (orderItem.getFlowerDTO().getIdFlower() == flower.getIdFlower()) {
                orderItem.setQtyFlower(orderItem.getQtyFlower() - 1);
                orderItem.setPriceFlower(flower.getPrice().multiply(new BigDecimal(orderItem.getQtyFlower())));
            }
            if (orderItem.getQtyFlower() == 0) {
                basket.getBasketList().remove(orderItem);
                return;
            }
        }
    }

    private void priceBasket(OrderDTO basket, int discount) {
        BigDecimal price = BigDecimal.ZERO;
        for (OrderItemDTO order : basket.getBasketList()) {
            price=price.add(order.getPriceFlower());
        }
        BigDecimal discountBig = new BigDecimal(discount).divide(new BigDecimal(100));
        price = price.subtract(price.multiply(discountBig));
        basket.setPriceSum(price.setScale(2));
    }
}
