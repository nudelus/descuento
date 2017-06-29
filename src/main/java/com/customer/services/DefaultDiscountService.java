package com.customer.services;


import com.customer.services.api.CustomerService;
import com.customer.services.api.DiscountService;
import com.customer.services.model.Customer;
import com.customer.services.model.Item;
import com.customer.services.model.ItemType;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class DefaultDiscountService implements DiscountService {

    private static final Double DISCOUNT_INTERVAL = 100D;
    private static final Double DISCOUNT = 5D;

    private static final ToDoubleFunction<Item> fullPrice;

    static {
        fullPrice = i -> i.getQuantity() * i.getPrice();
    }

    private final CustomerService customerService;

    public DefaultDiscountService(final CustomerService customerService) {
        this.customerService = customerService;
    }

    public Double calculateFinalPrize(final String customerId, final List<Item> items) {
        if (customerId == null || items == null) {
            throw new IllegalArgumentException();
        }

        Customer customer = customerService.getCustomer(customerId);

        Double groceriesPrice = getGroceriesPrice(items);
        Double discountItemPrice = getDiscountItemPrice(items, customer.calculateDiscount());
        Double billDiscount = getPriceDiscount(items);

        return groceriesPrice + discountItemPrice - billDiscount;
    }

    Double getGroceriesPrice(List<Item> items) {
        return getPriceSum(items, p -> ItemType.GROCERY.equals(p.getItemType()), fullPrice);
    }

    Double getDiscountItemPrice(List<Item> items, Double discount) {
        ToDoubleFunction<Item> discountPrice = i -> i.getQuantity() * (i.getPrice() - (discount != 0D ? i.getPrice() * discount : 0D));
        return getPriceSum(items, p -> !ItemType.GROCERY.equals(p.getItemType()), discountPrice);
    }

    Double getPriceDiscount(List<Item> items) {
        Double fullPrize = items.stream().mapToDouble(fullPrice).sum();

        int slots = (int) Math.floor(fullPrize / DISCOUNT_INTERVAL);

        return slots * DISCOUNT;
    }

    private Double getPriceSum(final List<Item> items, final Predicate<Item> filterPredicate, final ToDoubleFunction<Item> prizeCalculation) {
        return items.stream().filter(filterPredicate).mapToDouble(prizeCalculation).sum();
    }


}
