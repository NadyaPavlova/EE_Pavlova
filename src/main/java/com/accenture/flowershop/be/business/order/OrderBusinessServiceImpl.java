package com.accenture.flowershop.be.business.order;


import com.accenture.flowershop.be.access.order.OrderDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderDAO orderDao;
       private static final Logger LOG = LoggerFactory.getLogger(OrderDAO.class);


    public OrderBusinessServiceImpl() {
        LOG.info("CREATE:"+this.getClass()+";");
    }
}
