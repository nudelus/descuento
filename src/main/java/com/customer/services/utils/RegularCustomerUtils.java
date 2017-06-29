package com.customer.services.utils;


import java.time.LocalDate;

public class RegularCustomerUtils {

    private static final int YEARS_FOR_DISCOUNT = 2;

    public static boolean isRegularCustomer(LocalDate joinDate) {
        LocalDate discountThreshold = LocalDate.now().minusYears(YEARS_FOR_DISCOUNT);

        if (discountThreshold.isAfter(joinDate)) {
            return true;
        }

        return false;
    }

}
