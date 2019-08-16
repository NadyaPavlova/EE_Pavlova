package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
        this.entityManager.persist(order);
        this.entityManager.flush();
    }


        @Override
        public List<Order> getAllOrders () {
            TypedQuery<Order> q = entityManager.createQuery("select o from Order o order by (o.creationDate, o.status)", Order.class);
            return q.getResultList();
        }

        @Override
        public List<Order> getAllOrdersUser (Long id) throws InternalException {
            TypedQuery<Order> q = entityManager.createQuery("select o from Order o where o.user.id = :id", Order.class);
            q.setParameter("id", id);
            return q.getResultList();
        }

        @Override
        public Order getOrderById (Long id) throws NoResultException {
            TypedQuery<Order> q = entityManager.createQuery("select o from Order o where o.id = :id", Order.class);
            q.setParameter("id", id);
            return q.getSingleResult();
        }

        @Override
        @Transactional
        public void updateStatus (Order order) throws InternalException {
            Query q = entityManager.createQuery("update Order o set o.status = :st where o.idOrder = :id");
            q.setParameter("id", order.getId());
            q.setParameter("st", order.getStatus());
            q.executeUpdate();
            entityManager.flush();
        }

    }

