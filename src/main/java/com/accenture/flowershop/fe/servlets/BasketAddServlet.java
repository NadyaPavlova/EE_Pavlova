package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.InternalException;
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


@WebServlet(urlPatterns = "/user/BasketAddServlet")
public class BasketAddServlet extends HttpServlet {

    @Autowired
    FlowerBusinessService fbs;
    @Autowired
    DozerBeanMapper mapper;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long flowerId = Long.parseLong(req.getParameter("idFlower"));
            FlowerDTO flowerDTO = mapper.map(fbs.getFlowerById(flowerId), FlowerDTO.class);
            HttpSession session = req.getSession(false);
            OrderDTO basket = (OrderDTO) session.getAttribute("basket");
            UserDTO userDTO = (UserDTO) session.getAttribute("user");

            //определяем количество цветов для отправки в корзину, если строка пустая помещаем один цветок
            int qty = 1;
            if (!req.getParameter("quantity").isEmpty()) {
                qty = Integer.parseInt(req.getParameter("quantity"));
                if(qty<=0){
                    throw new InternalException(InternalException.ERROR_ADD_BASKET, new Throwable());
                }
            }

            addItemToBasket(basket, flowerDTO, qty);
            calcBasketTotalPrice(basket, userDTO.getDiscount());

            session.setAttribute("basket", basket);
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }catch (InternalException e) {
            e.printStackTrace();
            req.setAttribute("errorAddBasket",e.getMessage());
            req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
        }

    }

    private void addItemToBasket(OrderDTO basket, FlowerDTO flower, int qty) {
        for (OrderItemDTO order : basket.getItemList()) {
            if (order.getFlowerDTO().getIdFlower().equals(flower.getIdFlower())) {
                order.setQtyFlower(order.getQtyFlower() + qty);
                order.setPriceFlower(flower.getPrice().multiply(new BigDecimal(order.getQtyFlower())));
                return;
            }
        }
        OrderItemDTO orderItem = new OrderItemDTO();
        orderItem.setOrderDTO(basket);
        orderItem.setFlowerDTO(flower);
        orderItem.setQtyFlower(qty);
        orderItem.setPriceFlower(flower.getPrice().multiply(new BigDecimal(orderItem.getQtyFlower())));
        basket.getItemList().add(orderItem);
    }

    private void calcBasketTotalPrice(OrderDTO basket, int discount) {
        BigDecimal price = BigDecimal.ZERO;
        for (OrderItemDTO order : basket.getItemList()) {
            price=price.add(order.getPriceFlower());
        }
        BigDecimal discountBig = new BigDecimal(discount).divide(new BigDecimal(100));
        price = price.subtract(price.multiply(discountBig));
        basket.setPriceSum(price.setScale(2));
    }
}
