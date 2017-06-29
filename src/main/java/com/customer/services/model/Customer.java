package com.customer.services.model;


import com.customer.services.utils.RegularCustomerUtils;

import java.time.LocalDate;
import java.util.Date;

public class Customer {

    private static final Double CUSTOMER_DISCOUNT = 0.05D;

    private String customerId;

    private LocalDate joinDate;

    public Customer(String customerId, LocalDate joinDate) {
        this.customerId = customerId;
        this.joinDate = joinDate;
    }

    public String getCustomerId() {
        return customerId;
    }


    public LocalDate getJoinDate() {
        return joinDate;
    }


    public Double calculateDiscount() {
        if(RegularCustomerUtils.isRegularCustomer(joinDate)) {
            return CUSTOMER_DISCOUNT;
        }

        return 0D;
    }
}
