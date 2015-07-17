package com.github.alex_moon.tree.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.models.customers.ICustomerDAO;
import com.github.alex_moon.tree.models.users.IUserDAO;

@Controller
public class TreeController {
    @Autowired
    private IUserDAO userDao;

    @Autowired
    private ICustomerDAO customerDao;

    @RequestMapping(value="/")
    public String home() { return "home"; }

    @RequestMapping(value="/customers/")
    public ModelAndView customers() {
        ModelAndView model = new ModelAndView("customers");
        model.addObject("customers", customerDao.list());
        return model;
    }
}
