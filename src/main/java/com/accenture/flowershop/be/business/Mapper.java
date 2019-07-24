package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.orderItem.OrderItem;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static FlowerDTO mapper(Flower flower){
        FlowerDTO flowerDTO= new FlowerDTO();
        flowerDTO.setIdFlower(flower.getIdFlower());
        flowerDTO.setNameFlower(flower.getNameFlower());
        flowerDTO.setPrice(flower.getPrice());
        flowerDTO.setQtyStock(flower.getQtyStock());
        return flowerDTO;
    }
    public static Flower mapper(FlowerDTO flowerDTO){
        Flower flower= new Flower();
        flower.setIdFlower(flowerDTO.getIdFlower());
        flower.setNameFlower(flowerDTO.getNameFlower());
        flower.setPrice(flowerDTO.getPrice());
        flower.setQtyStock(flowerDTO.getQtyStock());
        return flower;
    }

    public static Order mapper(UserDTO userDTO, OrderDTO orderDTO){
        Order order = new Order();
        order.setUser(mapper(userDTO));
        order.setItemList(mapper(orderDTO.getBasketList(),order));
        order.setPriceSum(orderDTO.getPriceSum());
        order.setStatus(orderDTO.getStatus());
        return order;
    }

    public static User mapper(UserDTO userDTO){
        User user= new User();
        user.setAdmin(userDTO.getAdmin());
        user.setIdUser(userDTO.getIdUser());
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setFirstName(userDTO.getFirstName());
        user.setMoney(userDTO.getMoney());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDiscount(userDTO.getDiscount());
        return user;
    }

    public static List<OrderItem> mapper(List<OrderItemDTO> basketList,Order order){
        List<OrderItem> orderList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        for (OrderItemDTO oiDTO: basketList) {
            orderItem = mapper(oiDTO,order);
            orderList.add(orderItem);
        }
        return orderList;
    }

    public static OrderItem mapper(OrderItemDTO oiDTO, Order order){
        OrderItem orderItem = new OrderItem();
        orderItem.setFlower(Mapper.mapper(oiDTO.getFlowerDTO()));
        orderItem.setQtyFlower(oiDTO.getQtyFlower());
        orderItem.setOrder(order);
        orderItem.setPrice(oiDTO.getPriceFlower());
        return orderItem;
    }

    public static UserDTO mapper(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setAdmin(user.getAdmin());
        userDTO.setIdUser(user.getIdUser());
        userDTO.setLogin(user.getLogin());
        userDTO.setEmail(user.getEmail());
        userDTO.setLastName(user.getLastName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMoney(user.getMoney());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDiscount(user.getDiscount());
        return userDTO;
    }

    public static List<OrderDTO> mapper(List<Order> orderList){
        List<OrderDTO> orderListDTO = new ArrayList<>();
        for (Order order: orderList) {
            orderListDTO.add(mapper(order));
        }
        return orderListDTO;
    }
    public static OrderDTO mapper(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPriceSum(order.getPriceSum());
        orderDTO.setIdOrder(order.getIdOrder());
        orderDTO.setStatus(order.getStatus());
        for (OrderItem orderItem: order.getItemList()) {
            orderDTO.getBasketList().add(mapper(orderItem));
        }
        return orderDTO;
    }

    public static OrderItemDTO mapper(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setPriceFlower(orderItem.getPrice());
        orderItemDTO.setQtyFlower(orderItem.getQtyFlower());
        orderItemDTO.setFlowerDTO(mapper(orderItem.getFlower()));
        return orderItemDTO;
    }

}
