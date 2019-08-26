package com.accenture.flowershop.be.business.order;


import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.Secured;
import com.accenture.flowershop.be.business.StatusOrders;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.orderItem.OrderItem;
import com.accenture.flowershop.be.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserBusinessService ubs;

    @Autowired
    private FlowerBusinessService fbs;

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAO.class);


    public OrderBusinessServiceImpl() {
        LOG.info("CREATE:" + this.getClass() + ";");
    }

    @Override
    public Order getOrderById(Long id) throws InternalException {
        try {
            return orderRepository.getOrderById(id);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    @Secured
    public List<Order> getAllOrders(){

        return orderRepository.getByAdmin();
    }

    @Override
    public List<Order> getAllOrdersUser(Long id) throws InternalException {
        try {
            return orderRepository.getByUser(id);
        } catch (NoResultException e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void addOrder(Order order) throws InternalException {
        try {
            order.setCreationDate(LocalDate.now());
            order.setStatus(StatusOrders.GENERATED);
            orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER, new Throwable(e));
        }
    }

    @Override
    @Transactional(rollbackFor=InternalException.class)
    public void payOrder(Order order) throws InternalException {
        if ((StatusOrders.GENERATED) == (order.getStatus())) {
            ubs.payOrder(order.getUser(), order.getPriceSum());
            for (OrderItem orderItem : order.getItemList()) {
                fbs.countingFlowers(orderItem.getFlower(), orderItem.getQtyFlower());
            }
        } else {
            return;
        }
        order.setStatus(StatusOrders.PAID);
        orderRepository.save(order);
    }

    @Override
    @Transactional

    public void closedOrder(Long id) throws InternalException {
        Order order = getOrderById(id);
        order.close();
    }
}
