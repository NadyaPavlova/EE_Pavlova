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
    public Flower getFlowerByName(String name)throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("select f from Flower where f.nameFlower = :name", Flower.class);
            q.setParameter("name", name);
            return q.getSingleResult();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND, new Throwable(e));
        }

    }

    @Override
    public List<Flower> getAllFlowers() throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("select f from Flower f", Flower.class);
            return q.getResultList();
        }
       catch (Exception e){
           throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND, new Throwable(e));
       }
    }

    @Override
    @Transactional
    public void updateQtyStock(Flower flower)throws InternalException{
        try {
            Query q = entityManager.createQuery("update Flower f set f.qtyStock = :qty where f.idFlower = :id");
            q.setParameter("id", flower.getIdFlower());
            q.setParameter("qty", flower.getQtyStock());
            q.executeUpdate();
            entityManager.flush();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_UPDATE, new Throwable(e));
        }
    }

    @Override
    public List<Flower> searchFlower(String request)throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery( request, Flower.class);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND, new Throwable(e));
        }
    }
}
