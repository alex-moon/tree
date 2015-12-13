package com.github.alex_moon.tree.api.requests;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateSpend {
    @NotEmpty
    private String customerBarcode;

    @NotNull
    private BigDecimal spendAmount;

    @NotEmpty
    private String description;

    @NotNull
    private Date spendDate = new Date();

    public Date getSpendDate() {
        return spendDate;
    }
    public void setSpendDate(Date spendDate) {
        this.spendDate = spendDate;
    }
    public String getCustomerBarcode() {
        return customerBarcode;
    }
    public void setCustomerBarcode(String customerBarcode) {
        this.customerBarcode = customerBarcode;
    }

    public BigDecimal getSpendAmount() {
        return spendAmount;
    }
    public void setSpendAmount(BigDecimal spendAmount) {
        this.spendAmount = spendAmount;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
