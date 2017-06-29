package com.customer.services;

import com.customer.services.api.CustomerService;
import com.customer.services.model.*;
import com.customer.services.utils.RegularCustomerUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class DefaultDiscountServiceTest {

    private static final String BASE_CUST_ID = "CUID1";
    private static final String REGULAR_CUST_ID = "CUIDR2";
    private static final String AFFILIATE_CUST_ID = "CUIDA3";
    private static final String EMPLOYEE_CUST_ID = "CUIDE4";


    private CustomerService customerService;
    private DefaultDiscountService defaultDiscountService;
    private List<Item> itemList;

    @Before
    public void init() {
        this.customerService = mock(CustomerService.class);
        this.defaultDiscountService = new DefaultDiscountService(this.customerService);
        this.itemList = createItemList();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExcetion() {
        defaultDiscountService.calculateFinalPrize(null,itemList);
    }
    @Test
    public void testCalculateFinalPrizeNormal() {

        doReturn(new Customer(BASE_CUST_ID, LocalDate.of(2017, 02, 02))).when(customerService).getCustomer(BASE_CUST_ID);

        Double finalPrize = defaultDiscountService.calculateFinalPrize(BASE_CUST_ID, itemList);

        assertEquals(new Double(590), finalPrize);
    }

    @Test
    public void testCalculateFinalPrizeRegular() {

        doReturn(new Customer(REGULAR_CUST_ID, LocalDate.of(2015, 02, 02))).when(customerService).getCustomer(REGULAR_CUST_ID);

        Double finalPrize = defaultDiscountService.calculateFinalPrize(REGULAR_CUST_ID, itemList);

        assertEquals(new Double(569.5), finalPrize);
    }

    @Test
    public void testCalculateFinalPrizeAffiliate() {

        doReturn(new Affiliate(AFFILIATE_CUST_ID, LocalDate.of(2017, 02, 02))).when(customerService).getCustomer(AFFILIATE_CUST_ID);

        Double finalPrize = defaultDiscountService.calculateFinalPrize(AFFILIATE_CUST_ID, itemList);

        assertEquals(new Double(549), finalPrize);
    }

    @Test
    public void testCalculateFinalPrizeEmployee() {

        doReturn(new Employee(EMPLOYEE_CUST_ID, LocalDate.of(2017, 02, 02))).when(customerService).getCustomer(EMPLOYEE_CUST_ID);

        Double finalPrize = defaultDiscountService.calculateFinalPrize(EMPLOYEE_CUST_ID, itemList);

        assertEquals(new Double(467), finalPrize);
    }

    @Test
    public void testCustomerDiscountCalculation() {
        Double discountItemPrice = defaultDiscountService.getDiscountItemPrice(itemList, 0.1D);

        assertEquals(new Double(369), discountItemPrice);
    }

    @Test
    public void testGroceriesCalculation() {
        Double groceriesPrice = defaultDiscountService.getGroceriesPrice(itemList);

        assertEquals(new Double(210), groceriesPrice);
    }

    @Test
    public void testPriceDiscountCalculation() {
        Double discount = defaultDiscountService.getPriceDiscount(itemList);

        assertEquals(new Double(30), discount);
    }

    @Test
    public void testRegulerCustomerUtil() {

        assertFalse(RegularCustomerUtils.isRegularCustomer(LocalDate.of(2017, 02, 02)));
        assertTrue(RegularCustomerUtils.isRegularCustomer(LocalDate.of(2015, 06, 28)));
    }

    private List<Item> createItemList() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.ELECTRONICS, 1, 102D));
        items.add(new Item(ItemType.ELECTRONICS, 1, 215D));
        items.add(new Item(ItemType.GROCERY, 10, 20D));
        items.add(new Item(ItemType.HOUSEHOLD, 3, 31D));
        items.add(new Item(ItemType.GROCERY, 5, 2D));

        return items;
    }


}
