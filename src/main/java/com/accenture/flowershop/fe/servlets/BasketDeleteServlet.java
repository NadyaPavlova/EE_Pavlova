package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.OrderItemDTO;
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


@WebServlet(urlPatterns = "/user/BasketDeleteServlet")
public class BasketDeleteServlet extends HttpServlet {
    @Autowired
    private FlowerBusinessService fbs;
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flowerId = Long.parseLong(req.getParameter("idFlower"));
        FlowerDTO flowerDTO = mapper.map(fbs.getFlowerById(flowerId), FlowerDTO.class);
        HttpSession session = req.getSession(false);
        OrderDTO basket = (OrderDTO) session.getAttribute("basket");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        //удаляем цветок из корзины
        removeFlowerOfBasket(basket, flowerDTO);

        //считаем сумму корзины
        coutingPriceToBasket(basket, userDTO.getDiscount());

        session.setAttribute("basket", basket);

        req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
    }


    private void removeFlowerOfBasket(OrderDTO basket, FlowerDTO flower) {
        for (OrderItemDTO orderItem : basket.getItemList()) {
            if (orderItem.getFlowerDTO().getIdFlower() == flower.getIdFlower()) {
                orderItem.setQtyFlower(orderItem.getQtyFlower() - 1);
                orderItem.setPriceFlower(flower.getPrice().multiply(new BigDecimal(orderItem.getQtyFlower())));
            }
            if (orderItem.getQtyFlower() == 0) {
                basket.getItemList().remove(orderItem);
                return;
            }
        }
    }

    private void coutingPriceToBasket(OrderDTO basket, int discount) {
        BigDecimal price = BigDecimal.ZERO;
        for (OrderItemDTO order : basket.getItemList()) {
            price = price.add(order.getPriceFlower());
        }
        BigDecimal discountBig = new BigDecimal(discount).divide(new BigDecimal(100));
        price = price.subtract(price.multiply(discountBig));
        basket.setPriceSum(price.setScale(2));
    }
}
