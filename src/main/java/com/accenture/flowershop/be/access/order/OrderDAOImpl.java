package com.accenture.flowershop.be.access.order;

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
    public void saveOrder(Order order) {
        try {
            this.entityManager.persist(order);
            this.entityManager.flush();
        }catch (Exception e) {
            System.out.println("Ошибка создания заказа "+order.getIdOrder()+" в БД");
        }
    }


    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> q = entityManager.createQuery("select o from Order o", Order.class);
        return q.getResultList();
    }

    @Override
    public Order getOrderById(Long id) {
        TypedQuery<Order> q = entityManager.createQuery("select o from Order o where o.idOrder = :id", Order.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    @Transactional
    public void updateStatus(Order order){
        Query q = entityManager.createQuery("update Order o set o.status = :st where o.idOrder = :id");
        q.setParameter("id", order.getIdOrder());
        q.setParameter("st",order.getStatus());
        q.executeUpdate();
        entityManager.flush();
    }
}

