package com.github.alex_moon.tree.models.spend;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.alex_moon.tree.models.branches.Branch;
import com.github.alex_moon.tree.models.customers.Customer;


@Entity
@Table(name="spend")
public class Spend {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="branch_id")
    private Branch branch;

    @Column(name="spend_date")
    private Date spendDate;

    @Column(name="spend_value")
    private BigDecimal spendValue;

    @Column(name="description")
    private String description;

    public String toString() {
        return String.format(
            "%s (£%s from %s to %s at %s)",
            description, spendValue, customer, branch, spendDate
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getSpendDate() {
        return spendDate;
    }

    public void setSpendDate(Date spendDate) {
        this.spendDate = spendDate;
    }

    public BigDecimal getSpendValue() {
        return spendValue;
    }

    public void setSpendValue(BigDecimal spendValue) {
        this.spendValue = spendValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
