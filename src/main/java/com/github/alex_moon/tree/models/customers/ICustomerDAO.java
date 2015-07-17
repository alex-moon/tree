package com.github.alex_moon.tree.models.customers;

import java.util.List;

import com.github.alex_moon.tree.models.users.User;

public interface ICustomerDAO {
    public List<Customer> allocatedTo(User manager);
    public List<Customer> allUnder(User manager);
}
