package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class FlowerDAOImpl implements FlowerDAO {

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(FlowerDAOImpl.class);

    public FlowerDAOImpl() {
        LOG.info("CREATE:" + this.getClass() + ".");
    }

    @Override
    public Flower getFlowerById(Long id) {
        return entityManager.find(Flower.class, id);
    }

    @Override
    public List<Flower> getAllFlowers() throws InternalException {
        TypedQuery<Flower> q = entityManager.createQuery("select f from Flower f", Flower.class);
        return q.getResultList();
    }

    @Override
    @Transactional
    public void updateQtyStock(Flower flower) throws InternalException {
        Query q = entityManager.createQuery("update Flower f set f.qtyStock = :qty where f.id = :id");
        q.setParameter("id", flower.getId());
        q.setParameter("qty", flower.getQtyStock());
        q.executeUpdate();
        entityManager.flush();
    }

    @Override
    public List<Flower> searchFlower(String request) {
        TypedQuery<Flower> q = entityManager.createQuery("select f from Flower f " + request, Flower.class);
        return q.getResultList();
    }

    @Override
    public List<Flower> searchFlower(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        TypedQuery<Flower> q = entityManager.createQuery("select f from Flower f where f.nameFlower like '%"+name+"%' and f.price between :minPrice and :maxPrice", Flower.class);
        q.setParameter("minPrice", minPrice);
        q.setParameter("maxPrice", maxPrice);
        return q.getResultList();
    }

    @Override
    public void addFlowerQTY(Integer flowerQTY) {
        Query q = entityManager.createQuery("update Flower f set f.qtyStock = f.qtyStock + :qty");
        q.setParameter("qty", flowerQTY);
        q.executeUpdate();
        entityManager.flush();
    }
}
