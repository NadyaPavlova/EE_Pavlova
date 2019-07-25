package com.accenture.flowershop.be.business.order;


import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.business.InternalException;
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
    public Order getOrderById(Long id)  throws InternalException {
        try {
            return orderDao.getOrderById(id);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    public List<Order> getAllOrders() throws InternalException {
        try {
            return orderDao.getAllOrders();
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    public List<Order> getAllOrdersUser(Long id) throws InternalException {
        try {
            return orderDao.getAllOrdersUser(id);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void addOrder(Order order) throws InternalException{
        try {
            order.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
            order.setStatus("generated");
            orderDao.saveOrder(order);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void payOrder(Order order) throws InternalException{
        try {
            ubs.payOrder(order.getUser(), order.getPriceSum());
            for (OrderItem orderItem: order.getItemList()) {
                fbs.countingFlowers(orderItem.getFlower(),orderItem.getQtyFlower());
            }
            order.setStatus("paid");
            orderDao.updateStatus(order);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_UPDATE, new Throwable(e));
        }

    }

    @Override
    @Transactional
    public void closedOrder(Order order) throws InternalException{
        try {
            order.setStatus("closed");
            order.setClosingDate(new Date(Calendar.getInstance().getTime().getTime()));
            orderDao.updateStatus(order);
            orderDao.updateClosingDate(order);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_UPDATE, new Throwable(e));
        }
    }
}
