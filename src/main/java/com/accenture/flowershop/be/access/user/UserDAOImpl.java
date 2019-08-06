package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.business.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImpl() throws InternalException {

        LOG.info("CREATE:" + this.getClass() + ".");
    }


    @Override
    public User getUserByLogin(String login) throws NoResultException {
        TypedQuery<User> q;
        q = entityManager.createQuery("Select u from User u where u.login = :login", User.class);
        q.setParameter("login", login);
        return q.getSingleResult();
    }


    @Override
    public User getUserById(Long id) {
        TypedQuery<User> q;
        q = entityManager.createQuery("Select u from User u where u.idUser = :id", User.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }


    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void updateMoney(User user) {
        Query q = entityManager.createQuery("update User u set u.money = :balance where u.idUser = :id");
        q.setParameter("id", user.getIdUser());
        q.setParameter("balance", user.getMoney());
        q.executeUpdate();
        entityManager.flush();

    }

    @Override
    @Transactional
    public void updateDiscount(Long idUser, Integer discount) {
        Query q = entityManager.createQuery("update User u set u.discount = :discountnew where u.idUser = :id");
        q.setParameter("id", idUser);
        q.setParameter("discountnew", discount);
        q.executeUpdate();
        entityManager.flush();
    }
}
