package com.accenture.flowershop.fe.ws.mvc;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.annotation.SecuredAnnotation;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO getOrderById(@PathVariable("id") Long id){
        try{
            return mapper.map(orderBusinessService.getOrderById(id), OrderDTO.class);
        }
        catch (InternalException e) {
            return null;
        }
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrderAllById(@PathVariable("id") Long id){
        try{
            return mapperListDTO(orderBusinessService.getAllOrdersUser(id));
        }
        catch (InternalException e) {
            return null;
        }
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecuredAnnotation(onlyAdmin = false)
    public String payOrder(@PathVariable("id") Long id){
        try{
            orderBusinessService.payOrder(orderBusinessService.getOrderById(id));
            return "Good paid";
        }
        catch (InternalException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String payOrder(Order order){
        try{
            orderBusinessService.addOrder(order);
            return "Order is created";
        }
        catch (InternalException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecuredAnnotation(onlyAdmin = true)
    public List<OrderDTO> allOrders(){
        try{
            return mapperListDTO(orderBusinessService.getAllOrders());
        }
        catch (InternalException e) {
            return null;
        }
    }

    public List<OrderDTO> mapperListDTO(List<Order> ordersList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        OrderDTO orderDTO;
        for (Order order: ordersList) {
            orderDTO = mapper.map(order,OrderDTO.class);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }
}
