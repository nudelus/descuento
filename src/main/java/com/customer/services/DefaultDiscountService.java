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

    private CustomerService customerService;

    public DefaultDiscountService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Double calculateFinalPrize(String customerId, List<Item> items) {
        if(customerId == null || items == null) {
            throw new IllegalArgumentException();
        }

        final Customer customer = customerService.getCustomer(customerId);

        final Double discount = customer.calculateDiscount();

        ToDoubleFunction<Item> discountPrice = i -> i.getQuantity() * (i.getPrice()-(discount != 0D ? i.getPrice()*discount:0D));
        ToDoubleFunction<Item> fullPrice = i -> i.getQuantity() * i.getPrice();

        Double groceriesPrize = getPriceSum(items,p -> ItemType.GROCERY.equals(p.getItemType()),fullPrice);
        Double discountItemPrice = getPriceSum(items,p -> !ItemType.GROCERY.equals(p.getItemType()),discountPrice);

        Double billDiscount = getPriceDiscount(items,fullPrice);

        return  groceriesPrize + discountItemPrice - billDiscount;
    }

    private Double getPriceSum(List<Item> items, Predicate<Item> filterPredicate, ToDoubleFunction<Item> prizeCalculation) {
        return items.stream().filter(filterPredicate).mapToDouble(prizeCalculation).sum();
    }

    private Double getPriceDiscount(List<Item> items, ToDoubleFunction<Item> prizeCalculation) {
        Double fullPrize = items.stream().mapToDouble(prizeCalculation).sum();

        return  fullPrize/DISCOUNT_INTERVAL * DISCOUNT;
    }







}
