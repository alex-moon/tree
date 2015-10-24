package com.github.alex_moon.tree.models.branches;

import com.github.alex_moon.tree.models.users.User;

public interface IBranchDAO {
    public Branch getForUser(User user);
}
