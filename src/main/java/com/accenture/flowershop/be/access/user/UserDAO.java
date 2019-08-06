package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.NoResultException;

public interface UserDAO {
    User getUserByLogin(String login) throws NoResultException;
    void addUser(User user);
    void updateMoney(User user);
    void updateDiscount(Long idUser, Integer discount);
    User getUserById(Long id);
}
