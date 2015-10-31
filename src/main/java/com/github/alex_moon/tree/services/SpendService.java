package com.github.alex_moon.tree.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.branches.Branch;
import com.github.alex_moon.tree.models.branches.IBranchDao;
import com.github.alex_moon.tree.models.customers.Customer;
import com.github.alex_moon.tree.models.customers.ICustomerDao;
import com.github.alex_moon.tree.models.spend.ISpendDao;
import com.github.alex_moon.tree.models.spend.Spend;
import com.github.alex_moon.tree.models.users.User;

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
}
