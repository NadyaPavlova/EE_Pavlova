package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserDAO userDao;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public UserBusinessServiceImpl() {
        LOG.info("CREATE:" + this.getClass() + ";");

    }

    @Override
    public User login(String email, String password) {
        User user;
        if ((user = userDao.getUserByLogin(email)) != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

@Override
@Transactional
    public void registration(String email, String password , String firstName, String middleName, String lastName, String phoneNumber) {
        User user = new User(email, password , firstName, middleName, lastName, phoneNumber);
        user.setDiscount(3);
        user.setMoney(new BigDecimal(2000.0));

        userDao.addUser(user);
    }

    public void logout() {

    }
}
