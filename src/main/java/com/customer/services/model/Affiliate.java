package com.customer.services.model;


import java.time.LocalDate;

public class Affiliate extends Customer {

    private static final Double EFFILIATE_DISCOUNT = 0.1D;

    public Affiliate(String customerId, LocalDate joinDate) {
        super(customerId, joinDate);
    }

    @Override
    public Double calculateDiscount() {
        return EFFILIATE_DISCOUNT;
    }
}
