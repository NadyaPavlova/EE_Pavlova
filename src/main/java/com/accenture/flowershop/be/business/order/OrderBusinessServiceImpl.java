package com.accenture.flowershop.be.business.order;


import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.orderItem.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderDAO orderDao;

    @Autowired
    private UserBusinessService ubs;

    @Autowired
    private FlowerBusinessService fbs;

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAO.class);


    public OrderBusinessServiceImpl() {
        LOG.info("CREATE:"+this.getClass()+";");
    }

    @Override
    public Order getOrderById(Long id){
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getAllOrdersUser(Long id) {
        return orderDao.getAllOrdersUser(id);
    }

    @Override
    @Transactional
    public void addOrder(Order order){
        order.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
        order.setStatus("generated");
        orderDao.saveOrder(order);
    }

    @Override
    @Transactional
    public void payOrder(Order order){
        ubs.payOrder(order.getUser(), order.getPriceSum());
        for (OrderItem orderItem: order.getItemList()) {
            fbs.countingFlowers(orderItem.getFlower(),orderItem.getQtyFlower());
        }
        order.setStatus("paid");
        orderDao.updateStatus(order);
    }

    @Override
    @Transactional
    public void closedOrder(Order order){
        order.setStatus("closed");
        order.setClosingDate(new Date(Calendar.getInstance().getTime().getTime()));
        orderDao.updateStatus(order);
        orderDao.updateClosingDate(order);
    }

}
