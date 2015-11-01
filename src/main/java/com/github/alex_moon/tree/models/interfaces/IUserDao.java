package com.github.alex_moon.tree.models.interfaces;

import java.util.List;

import com.github.alex_moon.tree.models.User;

public interface IUserDao extends IBaseDao {
    public List<User> list();
    public User getByUsername(String username);
}
