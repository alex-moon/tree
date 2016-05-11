package com.github.alex_moon.tree.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.api.requests.CreateTransaction;
import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.models.interfaces.IBranchDao;
import com.github.alex_moon.tree.models.interfaces.ICustomerDao;
import com.github.alex_moon.tree.models.interfaces.ITransactionDao;
import com.github.alex_moon.tree.models.Transaction;

@Service
public class TransactionService {
    @Autowired
    private ITransactionDao transactionDao;
    
    @Autowired
    private ICustomerDao customerDao;
    
    @Autowired
    private IBranchDao branchDao;
    
    @Transactional(readOnly=true)
    public List<Transaction> getForUser(User user) {
        if (user.isRegionalManager()) {
            List<Branch> branches = branchDao.getForRegionalManager(user);
            return transactionDao.getForBranches(branches);
        } else if (user.isAreaManager()) {
            List<Branch> branches = branchDao.getForAreaManager(user);
            return transactionDao.getForBranches(branches);
        } else if (user.isBranchManager()) {
            Branch branch = branchDao.getForUser(user);
            return transactionDao.getForBranch(branch);
        } else if (user.isCustomer()) {
            Customer customer = customerDao.getForUser(user);
            return transactionDao.getForCustomer(customer);
        }
        return new ArrayList<Transaction>();
    }

    @Transactional
    public Transaction createTransaction(Customer customer, Branch branch, CreateTransaction request) {
        Transaction spend = new Transaction();
        spend.setBranch(branch);
        spend.setCustomer(customer);
        spend.setSpend(request.getSpend());
        spend.setDescription(request.getDescription());
        spend.setCreatedDate(request.getCreatedDate());

        transactionDao.persist(spend);
        return spend;
    }
}
