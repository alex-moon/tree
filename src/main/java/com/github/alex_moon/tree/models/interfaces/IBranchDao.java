package com.github.alex_moon.tree.models.interfaces;

import com.github.alex_moon.tree.models.User;

public interface IBranchDao {
    public Branch getForUser(User user);
}
