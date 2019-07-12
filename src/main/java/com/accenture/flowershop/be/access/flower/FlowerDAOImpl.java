package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.business.flower.FlowerBusinessServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FlowerDAOImpl implements FlowerDAO {


    private static final Logger LOG = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    public FlowerDAOImpl() {
        LOG.info("CREATE:" + this.getClass() + ".");
    }
}
