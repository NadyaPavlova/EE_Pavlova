package com.accenture.flowershop.fe.ws.mvc;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderBusinessService orderBusinessService;

    @Autowired
    DozerBeanMapper mapper;

    OrderController(){
        System.out.println("=== OrderController создан ===");
    }
    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO getOrder(){
        HttpSession session = SessionFactory.getSession(false);
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        return orderDTO;
    }

}
