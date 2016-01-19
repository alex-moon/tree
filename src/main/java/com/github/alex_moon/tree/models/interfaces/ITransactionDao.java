package com.github.alex_moon.tree.models.interfaces;

import java.util.List;

import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.Transaction;

public interface ITransactionDao extends IBaseDao {
    public List<Transaction> getForCustomer(Customer customer);
    public List<Transaction> getForBranch(Branch branch);
}
