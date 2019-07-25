package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.XMLConverter;
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

    @Autowired
    private XMLConverter xmlConverter;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public UserBusinessServiceImpl() {
        LOG.info("CREATE:" + this.getClass() + ";");
    }

    @Override
    public User login(String email, String password) throws InternalException {
        try {
            User user;
            if ((user = userDao.getUserByLogin(email)) != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable(e));
        }

    }

    @Override
    @Transactional
    public void registration(String email, String password , String lastName, String firstName, String middleName, String phoneNumber) throws InternalException {

        try {
            User user = new User(email, password, lastName , firstName, middleName, phoneNumber);
            user.setDiscount(3);
            user.setMoney(new BigDecimal(2000.0));
            userDao.addUser(user);
            xmlConverter.convertFromObjectToXML(getUserByLogin(email), "user" + getUserByLogin(email).getIdUser() + ".xml");
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable(e));
        }

    }

    @Override
    public User getUserByLogin(String login) throws InternalException{
        try {
            return userDao.getUserByLogin(login);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void payOrder(User user, BigDecimal price) throws InternalException{
        try {
            user.setMoney(user.getMoney().subtract(price));
            userDao.updateMoney(user);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_USER_UPDATE, new Throwable(e));
        }
    }
}
