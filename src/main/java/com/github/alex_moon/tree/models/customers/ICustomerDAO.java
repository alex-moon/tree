package com.github.alex_moon.tree.models.customers;

import java.util.List;

import com.github.alex_moon.tree.models.users.User;

public interface ICustomerDAO {
    public List<Customer> getAllAllocatedTo(User manager);
    public List<Customer> getAllUnder(User manager);
}
