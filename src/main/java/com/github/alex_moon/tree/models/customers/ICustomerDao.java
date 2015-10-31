package com.github.alex_moon.tree.models.customers;

import com.github.alex_moon.tree.models.users.User;

public interface ICustomerDao {
    public Customer getForUser(User user);
}
