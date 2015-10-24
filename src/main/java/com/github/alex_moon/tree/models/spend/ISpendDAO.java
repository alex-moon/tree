package com.github.alex_moon.tree.models.spend;

import java.util.List;

import com.github.alex_moon.tree.models.branches.Branch;
import com.github.alex_moon.tree.models.customers.Customer;
import com.github.alex_moon.tree.models.users.User;

public interface ISpendDAO {
    public List<Spend> getForUser(User user);
    public List<Spend> getForCustomer(Customer customer);
    public List<Spend> getForBranch(Branch branch);
}
