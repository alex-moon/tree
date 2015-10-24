package com.github.alex_moon.tree.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.models.customers.ICustomerDAO;
import com.github.alex_moon.tree.models.users.IUserDAO;
import com.github.alex_moon.tree.models.users.User;

@Controller
public class TreeController {
    @Autowired
    private IUserDAO userDao;

    @Autowired
    private ICustomerDAO customerDao;

    @RequestMapping(value="/")
    public String home() { return "home"; }

    @RequestMapping(value="/customers/")
    public ModelAndView customers(Principal principal) {
        ModelAndView model = new ModelAndView("customers");
        User manager = userDao.getByUsername(principal.getName());
        model.addObject("customers", customerDao.getAllUnder(manager));
        return model;
    }

    @RequestMapping(value="/spend/")
    public ModelAndView spend(Principal principal) {
        ModelAndView model = new ModelAndView("customers");
        User manager = userDao.getByUsername(principal.getName());
        model.addObject("customers", customerDao.getAllUnder(manager));
        return model;
    }
}
