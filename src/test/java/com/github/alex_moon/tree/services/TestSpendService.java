package com.github.alex_moon.tree.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.alex_moon.tree.models.Transaction;
import com.github.alex_moon.tree.models.User;

public class TestSpendService {
    private TransactionService transactionService = new TransactionService();
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetForNonCustomerBranchUser() {
        User user = new User();
        List<Transaction> spend = transactionService.getForUser(user);
        Assert.assertEquals(spend.size(), 0);
    }
}
