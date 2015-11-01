package com.github.alex_moon.tree.models.interfaces;

import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.User;

public interface ICustomerDao extends IBaseDao {
    public Customer getForUser(User user);
}
