package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.user.User;

import java.math.BigDecimal;

public interface UserBusinessService {
    User login(String user, String password) throws InternalException;
    void registration(String email, String middleName, String firstName, String lastName, String phoneNumber, String password) throws InternalException;
    User getUserByLogin(String login) throws InternalException;
    void payOrder(User user, BigDecimal price) throws InternalException;
    void setDiscount(Long idUser, Integer discount);
}
