package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/personalAccountServlet")
public class PersonalAccountServlet extends HttpServlet {
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    DozerBeanMapper mapper;

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
            userDTO = mapper.map(userBusinessService.getUserByLogin(userDTO.getLogin()), UserDTO.class);
            session.setAttribute("user", userDTO);


            OrderDTO basket = (OrderDTO) session.getAttribute("basket");

            //Проверка на кол-во доступных товаров и доступных средст у покупателя
            session.setAttribute("errCount", stockAvailability(basket));
            session.setAttribute("basket", basket);


            //обновление цветов
            if(req.getAttribute("flowerStockFilter")!= null){
                req.setAttribute("flowers",  mapperFlower((List<Flower>) req.getAttribute("flowerStockFilter")));
            }
            else {
                req.setAttribute("flowers", mapperFlower(flowerBusinessService.getAllFlowers()));
            }

            if(session.getAttribute("role").equals("Admin")){
                req.setAttribute("orders", mapperOrder(orderBusinessService.getAllOrders()));
            }
            else {
                //обновление заказов
                req.setAttribute("orders", mapperOrder(orderBusinessService.getAllOrdersUser(userDTO.getIdUser())));
            }
            req.getRequestDispatcher("/personalAccount.jsp").forward(req, resp);
        }
        catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorLoginPassword","Произошла ошибка. Повторите вход в систему");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    private String stockAvailability(OrderDTO basket) {
        for (OrderItemDTO fl : basket.getItemList()) {
            if (fl.getQtyFlower() > flowerBusinessService.getFlowerById(fl.getFlowerDTO().getIdFlower()).getQtyStock()) {
                return "Товара нет в наличии. ";
            }
        }
        return "";
    }

    public List<OrderDTO> mapperOrder(List<Order> ordersList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        OrderDTO orderDTO;
        for (Order order: ordersList) {
            orderDTO = mapper.map(order,OrderDTO.class);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    public List<FlowerDTO> mapperFlower(List<Flower> flowersList){
        List<FlowerDTO> flowerDTOList = new ArrayList<>();
        FlowerDTO flowerDTO;
        for (Flower flower: flowersList) {
            flowerDTO = mapper.map(flower,FlowerDTO.class);
            flowerDTOList.add(flowerDTO);
        }
        return flowerDTOList;
    }
}
