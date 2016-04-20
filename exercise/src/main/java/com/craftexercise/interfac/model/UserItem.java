package com.craftexercise.interfac.model;

import java.math.BigDecimal;

public class UserItem extends BaseItem{
    private Integer itemId;

    public UserItem( Integer itemId, String name, BigDecimal price, String category)
    {
        super(name,price,category);
        this.itemId = itemId;
    }

    public Integer getItemId()
    {
        return itemId;
    }

    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }
    
}
