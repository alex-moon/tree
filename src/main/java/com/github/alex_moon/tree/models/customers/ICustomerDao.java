package com.github.alex_moon.tree.models.customers;

import com.github.alex_moon.tree.models.IBaseDao;
import com.github.alex_moon.tree.models.users.User;

public interface ICustomerDao extends IBaseDao<Customer> {
    public Customer getForUser(User user);
}
