package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.Mapper;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
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

@WebServlet(urlPatterns = "/personalAccountServlet")
public class personalAccountServlet extends HttpServlet {
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            HttpSession session = req.getSession(false);

            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            //обновляем данные пользователя
            userDTO = Mapper.mapper(userBusinessService.getUserByLogin(userDTO.getLogin()));
            session.setAttribute("user", userDTO);


            OrderDTO basket = (OrderDTO) session.getAttribute("basket");

            //Проверка на кол-во доступных товаров и доступных средст у покупателя
            session.setAttribute("errCount", stockAvailability(basket));
            session.setAttribute("errPrice", checkBasketPrice(basket, userDTO.getMoney()));
            session.setAttribute("basket", basket);


            //обновление цветов
            if(req.getAttribute("flowerStockFilter")!= null){
                req.setAttribute("flowers", req.getAttribute("flowerStockFilter"));
            }
            else {
            req.setAttribute("flowers", flowerBusinessService.getAllFlowers());}
            if(session.getAttribute("role").equals("Admin")){
                req.setAttribute("orders", Mapper.mapper(orderBusinessService.getAllOrders()));
            }
            else {
                //обновление заказов
                req.setAttribute("orders", Mapper.mapper(orderBusinessService.getAllOrdersUser(userDTO.getIdUser())));
            }
            req.getRequestDispatcher("/personalAccount.jsp").forward(req, resp);
        }
        catch (Exception e){

        }


     /*   if(userDto.getRole().equals("user")) {
            req.getRequestDispatcher("/mainPage.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("/adminMainPage.jsp").forward(req, resp);
        }*/
    }

    private String stockAvailability(OrderDTO basket) {
        for (OrderItemDTO fl : basket.getBasketList()) {
            if (fl.getQtyFlower() > flowerBusinessService.getFlowerById(fl.getFlowerDTO().getIdFlower()).getQtyStock()) {
                return "This number of flowers is not in stock";
            }
        }
        return "";
    }


    private String checkBasketPrice(OrderDTO basket, BigDecimal money) {
        if(basket.getPriceSum().compareTo(money)<0) {
            return "";
        }
        else {
            return "Not enough money";
        }
    }

/*    private void basketDiscount(OrderDTO basket, int discount) {
        if (basket.getPriceSum().compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal discountBig = new BigDecimal(discount).divide(new BigDecimal(100));
            basket.setPriceSum(basket.getPriceSum().subtract(basket.getPriceSum().multiply(discountBig)));
            basket.setPriceSum(basket.getPriceSum().setScale(2));
        }
    }*/
}
