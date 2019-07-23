package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

import java.math.BigDecimal;

public interface UserBusinessService {
    User login(String user, String password);
    void logout();
    void registration(String email, String middleName, String firstName, String lastName, String phoneNumber, String password);
    User getUserByLogin(String login);
    void payOrder(User user, BigDecimal price);
}
