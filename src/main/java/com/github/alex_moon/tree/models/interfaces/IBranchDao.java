package com.github.alex_moon.tree.models.interfaces;

import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.User;

public interface IBranchDao extends IBaseDao {
    public Branch getForUser(User user);
}
