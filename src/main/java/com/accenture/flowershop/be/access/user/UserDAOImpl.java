package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.business.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImpl() throws InternalException {

        LOG.info("CREATE:" + this.getClass() + ".");
    }


  @Override
   public User getUserByLogin(String login) throws InternalException{
        try {
            TypedQuery<User> q;
            q = entityManager.createQuery("Select u from User u where u.login = :login", User.class);
            q.setParameter("login", login);
            return q.getSingleResult();
        }
        catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public void addUser(User user) throws InternalException {
        try {
            entityManager.persist(user);
            entityManager.flush();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_USER, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void updateMoney(User user) throws InternalException{
        try {
            Query q = entityManager.createQuery("update User u set u.money = :balance where u.idUser = :id");
            q.setParameter("id", user.getIdUser());
            q.setParameter("balance", user.getMoney());
            q.executeUpdate();
            entityManager.flush();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_USER_UPDATE, new Throwable(e));
        }

    }

    @Override
    @Transactional
    public void updateDiscount(Long idUser, Integer discount){

            Query q = entityManager.createQuery("update User u set u.discount = :discountnew where u.idUser = :id");
            q.setParameter("id", idUser);
            q.setParameter("discountnew", discount);
            q.executeUpdate();
            entityManager.flush();
        }
}
