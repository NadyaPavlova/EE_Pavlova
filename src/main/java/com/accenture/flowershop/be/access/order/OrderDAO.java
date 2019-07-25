package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderDAO {
    void saveOrder(Order order) throws InternalException;
    List<Order> getAllOrders() throws InternalException;
    void updateStatus(Order order) throws InternalException;
    Order getOrderById(Long id) throws InternalException;
    List<Order> getAllOrdersUser(Long id) throws InternalException;
    void updateClosingDate (Order order) throws InternalException;
}
