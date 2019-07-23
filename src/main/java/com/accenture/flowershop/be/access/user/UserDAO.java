package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserDAO {
    public User getUserByLogin(String login);
    public void addUser(User user);
    void updateMoney(User user);
}
