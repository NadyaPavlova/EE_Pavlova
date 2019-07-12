package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.access.flower.FlowerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerDAO flowerDao;
    private static final Logger LOG = LoggerFactory.getLogger(FlowerDAO.class);


    public FlowerBusinessServiceImpl() {
        LOG.info("CREATE:"+this.getClass()+";");
    }
}
