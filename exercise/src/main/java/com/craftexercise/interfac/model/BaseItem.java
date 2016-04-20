package com.craftexercise.interfac.model;

import java.math.BigDecimal;

public class BaseItem
{
    private String itemName;
    private BigDecimal itemPrice;
    private String category;

    public BaseItem(String name, BigDecimal price, String category)
    {
        this.itemName = name;
        this.itemPrice = price;
        this.category = category;
    }
    public String getItemName()
    {
        return itemName;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    public BigDecimal getItemPrice()
    {
        return itemPrice;
    }
    public void setItemPrice(BigDecimal itemPrice)
    {
        this.itemPrice = itemPrice;
    }
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }
}
