package com.github.alex_moon.tree.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.models.IUserDAO;
import com.github.alex_moon.tree.models.User;

@Controller
public class TreeController {
     
    @Autowired
    private IUserDAO userDao;
     
    @RequestMapping(value="/")
    public ModelAndView home() {
        List<User> listUsers = userDao.list();
        ModelAndView model = new ModelAndView("home");
        model.addObject("userList", listUsers);
        return model;
    }     
}
