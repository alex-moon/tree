package com.github.alex_moon.tree.services;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.github.alex_moon.tree.api.ApiException;
import com.github.alex_moon.tree.api.requests.CreateCustomer;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.models.interfaces.ICustomerDao;
import com.github.alex_moon.tree.models.interfaces.IUserDao;

@Service
public class CustomerService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICustomerDao customerDao;

    @Transactional
    public Customer createCustomer(CreateCustomer request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setUserTypeCustomer(); 

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setName(request.getName());
        customer.setBarcode(generateBarcode());

        userDao.persist(user);
        customerDao.persist(customer);
        return customer;
    }

    private String generateBarcode() {
        return String.format("TR%sEE", RandomStringUtils.randomNumeric(6));
    }
}
