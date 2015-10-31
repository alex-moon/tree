package com.github.alex_moon.tree.models.branches;

import com.github.alex_moon.tree.models.users.User;

public interface IBranchDao {
    public Branch getForUser(User user);
}
