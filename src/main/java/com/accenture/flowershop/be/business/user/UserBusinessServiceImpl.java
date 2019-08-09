package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.business.JmsConfig;
import com.accenture.flowershop.be.business.XMLConverter;
import com.accenture.flowershop.be.entity.user.User;
import org.h2.message.DbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserDAO userDao;

    @Autowired
    private XMLConverter xmlConverter;

    @Autowired
    private JmsConfig jmsConfig;

    @Value("${exportPath}")
    String properyPath;
    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessService.class);

    public UserBusinessServiceImpl() {
        LOG.info("CREATE:" + this.getClass() + ";");
    }

    @Override
    public User login(String email, String password) throws InternalException {

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
    public void registration(String email, String password, String lastName, String firstName, String middleName, String phoneNumber) throws InternalException {

        try {
            User user = new User(email, password, lastName, firstName, middleName, phoneNumber);
            user.setDiscount(3);
            user.setMoney(new BigDecimal(2000.0));
            userDao.addUser(user);
            // отправляем пользователя в очередь и сохроняем его в xml
            xmlConverter.convertFromObjectToXML(user, "user" + user.getIdUser() + ".xml");
            jmsConfig.sendInOutQueue("user" + user.getIdUser() + ".xml");
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable(e));
        }

    }

    @Override
    public User getUserByLogin(String login) throws InternalException {
        try {
            return userDao.getUserByLogin(login);
        } catch (Exception e) {
            throw new InternalException(InternalException.ERROR_DAO_USER_FIND, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public void payOrder(User user, BigDecimal price) throws InternalException {
        try {
            if ((user.getMoney().compareTo(price) >= 0) && (price.compareTo(BigDecimal.ZERO)>=0)){
                user.pay(price);
                userDao.updateMoney(user);
            } else {
                throw new InternalException(InternalException.ERROR_DAO_ORDER_UPDATE, new Throwable());
            }
        } catch (DbException e) {
            throw e;
        }
    }

    @Override
    public void setDiscount(Long idUser, Integer discount) {
        userDao.updateDiscount(idUser, discount);
    }
}
