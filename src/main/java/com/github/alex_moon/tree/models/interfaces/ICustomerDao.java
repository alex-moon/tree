package com.github.alex_moon.tree.models.interfaces;

import com.github.alex_moon.tree.models.interfaces.IBaseDao;
import com.github.alex_moon.tree.models.User;

public interface ICustomerDao extends IBaseDao<Customer> {
    public Customer getForUser(User user);
}
