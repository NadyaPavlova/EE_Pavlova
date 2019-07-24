package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerDAO flowerDao;
    private static final Logger LOG = LoggerFactory.getLogger(FlowerDAO.class);


    public FlowerBusinessServiceImpl() {
        LOG.info("CREATE:"+this.getClass()+";");
    }

    @Override
    public List<Flower> getAllFlowers() {
        return flowerDao.getAllFlowers();
    }

    @Override
    public Flower getFlowerById(Long id){
        return flowerDao.getFlowerById(id);
    }

    @Override
    @Transactional
    public void countingFlowers(Flower flower, int qty){
        flower.setQtyStock(flower.getQtyStock()- qty);
        flowerDao.updateQtyStock(flower);
    }

    @Override
    public List<Flower> searchFlower(String request){
        return flowerDao.searchFlower(request);
    }

}
