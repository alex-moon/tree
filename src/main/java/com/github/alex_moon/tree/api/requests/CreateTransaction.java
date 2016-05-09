package com.github.alex_moon.tree.api.requests;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateTransaction {
    @NotEmpty
    private String customerBarcode;

    @NotNull
    private BigDecimal spend;

    @NotEmpty
    private String description;

    @NotNull
    private Date createdDate = new Date();

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getCustomerBarcode() {
        return customerBarcode;
    }
    public void setCustomerBarcode(String customerBarcode) {
        this.customerBarcode = customerBarcode;
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
