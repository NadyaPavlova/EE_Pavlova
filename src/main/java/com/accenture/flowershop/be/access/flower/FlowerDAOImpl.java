package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public Flower getFlowerByName(String name) {
        TypedQuery<Flower> q = entityManager.createQuery("select f from Flower where f.nameFlower = :name", Flower.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    @Override
    public List<Flower> getAllFlowers() {
        TypedQuery<Flower> q = entityManager.createQuery("select f from Flower f", Flower.class);
        return q.getResultList();
    }
}
