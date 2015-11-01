package com.github.alex_moon.tree.models.interfaces;

import java.util.List;

import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.User;

public interface ISpendDao {
    public List<Spend> getForCustomer(Customer customer);
    public List<Spend> getForBranch(Branch branch);
}
