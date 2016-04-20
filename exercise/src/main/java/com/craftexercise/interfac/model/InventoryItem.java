package com.craftexercise.interfac.model;

import java.math.BigDecimal;

public class InventoryItem extends UserItem{

    private int quantity;

    public InventoryItem(Integer itemId, String name, BigDecimal price, String category,  int quantity)
    {
        super(itemId, name, price, category);
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
}
