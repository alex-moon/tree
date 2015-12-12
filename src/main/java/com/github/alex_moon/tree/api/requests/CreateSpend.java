package com.github.alex_moon.tree.api.requests;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;

public class CreateSpend {
    @NotNull
    private Customer customer;

    @NotNull
    private Branch branch;

    @NotNull
    private BigDecimal spendAmount;

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public BigDecimal getSpendAmount() {
        return spendAmount;
    }
    public void setSpendAmount(BigDecimal spendAmount) {
        this.spendAmount = spendAmount;
    }
}
