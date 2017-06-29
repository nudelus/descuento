package com.customer.services.api;


import com.customer.services.model.Item;

import java.util.List;

public interface DiscountService {

    Double calculateFinalPrize(String customerId, List<Item> items);
}
