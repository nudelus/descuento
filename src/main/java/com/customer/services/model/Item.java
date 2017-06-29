package com.customer.services.model;


public class Item {

    private ItemType itemType;

    private Integer quantity;

    private Double price;


    public Item(ItemType itemType, Integer quantity, Double price) {
        this.itemType = itemType;
        this.quantity = quantity;
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

}
