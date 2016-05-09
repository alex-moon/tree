package com.github.alex_moon.tree.models;

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

import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;


@Entity
@Table(name="transactions")
public class Transaction implements Model {
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

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="spend")
    private BigDecimal spend;

    @Column(name="description")
    private String description;

    public String toString() {
        return String.format(
            "%s (£%s from %s to %s at %s)",
            description, spend, customer, branch, createdDate
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getSpend() {
        return spend;
    }

    public void setSpend(BigDecimal spend) {
        this.spend = spend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
