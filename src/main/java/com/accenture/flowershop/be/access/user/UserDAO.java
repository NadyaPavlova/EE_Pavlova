package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.user.User;

public interface UserDAO {
    User getUserByLogin(String login) throws InternalException;
    void addUser(User user) throws InternalException;
    void updateMoney(User user) throws InternalException;
    void updateDiscount(Long idUser, Integer discount);
    User getUserById(Long id);
}
