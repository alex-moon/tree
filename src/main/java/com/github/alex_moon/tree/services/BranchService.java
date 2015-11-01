package com.github.alex_moon.tree.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.api.requests.CreateBranch;
import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.models.interfaces.IBranchDao;
import com.github.alex_moon.tree.models.interfaces.IUserDao;

@Service
public class BranchService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBranchDao branchDao;

    @Transactional
    public Branch createBranch(CreateBranch request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setUserTypeBranchManager();

        Branch branch = new Branch();
        branch.setUser(user);
        branch.setName(request.getName());
        branch.setPostcode(request.getPostcode());

        userDao.persist(user);
        branchDao.persist(branch);
        return branch;
    }
}
