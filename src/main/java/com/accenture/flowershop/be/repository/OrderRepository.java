package com.accenture.flowershop.be.repository;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderById(long id);
    @Query(value = "select o from Order o order by (o.creationDate, o.status)")
    List<Order> getAllOrders();
    @Query(value = "select o from Order o where o.user.id = :id")
    List<Order> getByUser(@Param("id")Long id);
    Order saveAndFlush(Order order);
}
