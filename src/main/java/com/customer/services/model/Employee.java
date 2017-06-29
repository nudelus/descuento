package com.customer.services.model;


import java.time.LocalDate;

public class Employee extends Customer {

    private static final Double EMPLOYEE_DISCOUNT = 0.3D;

    public Employee(String customerId, LocalDate joinDate) {
        super(customerId, joinDate);
    }


    @Override
    public Double calculateDiscount() {
        return EMPLOYEE_DISCOUNT;
    }
}
