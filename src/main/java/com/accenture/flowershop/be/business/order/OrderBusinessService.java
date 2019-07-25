package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllOrdersUser(Long id) throws InternalException;
    void addOrder(Order order) throws InternalException;
    List<Order> getAllOrders() throws InternalException;
    void payOrder(Order order) throws InternalException;
    Order getOrderById(Long id) throws InternalException;
    void closedOrder(Order order) throws InternalException;
}
