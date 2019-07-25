package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public OrderDAOImpl() {
    }

    @Override
    @Transactional
    public void saveOrder(Order order) throws InternalException {
        try {
            this.entityManager.persist(order);
            this.entityManager.flush();
        }catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER, new Throwable(e));
        }
    }


    @Override
    public List<Order> getAllOrders() throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("select o from Order o", Order.class);
            return q.getResultList();
        }catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
    }

    }

    @Override
    public List<Order> getAllOrdersUser(Long id) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("select o from Order o where o.user.idUser = :id", Order.class);
            q.setParameter("id", id);
            return q.getResultList();
        }catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    public Order getOrderById(Long id) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("select o from Order o where o.idOrder = :id", Order.class);
            q.setParameter("id", id);
            return q.getSingleResult();
        }catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void updateStatus(Order order) throws InternalException{
        try {
            Query q = entityManager.createQuery("update Order o set o.status = :st where o.idOrder = :id");
            q.setParameter("id", order.getIdOrder());
            q.setParameter("st",order.getStatus());
            q.executeUpdate();
            entityManager.flush();
        }
        catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_UPDATE, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void updateClosingDate (Order order)throws InternalException{
        try {
            Query q = entityManager.createQuery("update Order o set o.closingDate = :date where o.idOrder = :id");
            q.setParameter("id", order.getIdOrder());
            q.setParameter("date",order.getClosingDate());
            q.executeUpdate();
            entityManager.flush();
        }
        catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_ORDER_UPDATE, new Throwable(e));
        }
    }
}

