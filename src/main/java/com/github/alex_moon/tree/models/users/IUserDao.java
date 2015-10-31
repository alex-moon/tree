package com.github.alex_moon.tree.models.users;

import java.util.List;

public interface IUserDao {
    public List<User> list();
    public User getByUsername(String username);
}
