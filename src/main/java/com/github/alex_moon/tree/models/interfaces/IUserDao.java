package com.github.alex_moon.tree.models.interfaces;

import java.util.List;

import com.github.alex_moon.tree.models.interfaces.IBaseDao;

public interface IUserDao extends IBaseDao<User> {
    public List<User> list();
    public User getByUsername(String username);
}
