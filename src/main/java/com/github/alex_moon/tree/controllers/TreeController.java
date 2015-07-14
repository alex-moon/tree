package com.github.alex_moon.tree.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.models.IUserDAO;

@Controller
public class TreeController {
    @Autowired
    private IUserDAO userDao;

    @RequestMapping(value="/")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("home");
        model.addObject("userList", userDao.list());
        return model;
    }     
}
