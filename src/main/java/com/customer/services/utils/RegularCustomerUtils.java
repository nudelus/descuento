package com.customer.services.utils;


import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

public class RegularCustomerUtils {

    private static final int YEARS_FOR_DISCOUNT = 2;

    public static boolean isRegularCustomer(LocalDate joinDate) {
        LocalDate now = LocalDate.now();

        if(now.minusYears(2).isAfter(joinDate)) {
            return true;
        }

        return false;
    }

}
