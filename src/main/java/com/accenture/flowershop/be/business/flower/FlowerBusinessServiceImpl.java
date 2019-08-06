package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerDAO flowerDao;
    private static final Logger LOG = LoggerFactory.getLogger(FlowerDAO.class);


    public FlowerBusinessServiceImpl() {
        LOG.info("CREATE:" + this.getClass() + ";");
    }

    @Override
    public List<Flower> getAllFlowers() throws InternalException {

        return flowerDao.getAllFlowers();
    }

    @Override
    public Flower getFlowerById(Long id) {
        return flowerDao.getFlowerById(id);
    }

    @Override
    @Transactional
    public void countingFlowers(Flower flower, int qty) throws InternalException {
        if (qty <= flower.getQtyStock()) {
            flower.reduceQtyStock(qty);
            flowerDao.updateQtyStock(flower);
        } else {
           throw new InternalException(InternalException.ERROR_DAO_FLOWERS_PAY, new Throwable());
        }
    }

    @Override
    public void addFlowerQTY(Integer flowerQTY) {
        flowerDao.addFlowerQTY(flowerQTY);
    }


    @Override
    public List<Flower> filterFlower(String name, String minPrice, String maxPrice) {
        BigDecimal min= new BigDecimal(00.00);
        BigDecimal max= new BigDecimal(10000.00);
        if(!minPrice.isEmpty()){
            min= new BigDecimal(minPrice);
        }
        if(!maxPrice.isEmpty()){
            max= new BigDecimal(maxPrice);
        }
        return flowerDao.searchFlower(name, min, max);
    }

}
