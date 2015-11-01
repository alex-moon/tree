package com.github.alex_moon.tree.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.api.requests.CreateCustomer;
import com.github.alex_moon.tree.models.customers.Customer;
import com.github.alex_moon.tree.models.customers.ICustomerDao;
import com.github.alex_moon.tree.models.users.IUserDao;
import com.github.alex_moon.tree.models.users.User;

@Service
public class CustomerService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICustomerDao customerDao;

    @Transactional
    public Customer createCustomer(CreateCustomer request) {
        User user = userDao.create();
        user.setUserTypeCustomer();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        Customer customer = customerDao.create();
        customer.setUser(user);
        customer.setName(request.getName());
        customer.setBarcode(generateBarcode());

        return customer;
    }
    
    private String generateBarcode() {
        return "TR" + RandomStringUtils.randomNumeric(6);
    }
}
