package com.github.alex_moon.tree.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.api.requests.CreateSpend;
import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.Spend;
import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.models.interfaces.IBranchDao;
import com.github.alex_moon.tree.models.interfaces.ICustomerDao;
import com.github.alex_moon.tree.models.interfaces.ISpendDao;

@Service
public class SpendService {
    @Autowired
    private ISpendDao spendDao;
    
    @Autowired
    private ICustomerDao customerDao;
    
    @Autowired
    private IBranchDao branchDao;
    
    @Transactional
    public List<Spend> getForUser(User user) {
        if (user.isBranchManager()) {
            Branch branch = branchDao.getForUser(user);
            return spendDao.getForBranch(branch);
        } else if (user.isCustomer()) {
            Customer customer = customerDao.getForUser(user);
            return spendDao.getForCustomer(customer);
        }
        return new ArrayList<Spend>();
    }

    @Transactional
    public Spend createSpend(CreateSpend createSpend) {
        throw new RuntimeException("cunt a doodle doo bitch");
    }
}
