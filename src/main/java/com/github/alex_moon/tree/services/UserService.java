package com.github.alex_moon.tree.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.models.interfaces.IUserDao;

@Service
public class UserService {
    @Autowired
    private IUserDao userDao;
    
    @Transactional
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
