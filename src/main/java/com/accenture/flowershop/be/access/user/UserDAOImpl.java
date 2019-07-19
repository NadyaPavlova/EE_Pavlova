package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImpl() {

        LOG.info("CREATE:" + this.getClass() + ".");
    }


  @Override
   public User getUserByLogin(String login) {
      TypedQuery<User> q;
      q = entityManager.createQuery("Select u from User u where u.login = :login", User.class);
      q.setParameter("login", login);
      return q.getSingleResult();
    }


    @Override
    @Transactional
    public void addUser(User user){
        entityManager.persist(user);
        entityManager.flush();
    }

}
