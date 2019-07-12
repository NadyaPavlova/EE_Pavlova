package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserBusinessService {
    User login(String user, String password);
    void logout();
    void registration(String email, String middleName, String firstName, String lastName, String phoneNumber, String password);
}
