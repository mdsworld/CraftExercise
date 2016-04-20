package com.craftexercise.interfac;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PlaceOrderRequest
{
    @NotNull(message = "'itemIds' is required")
    private java.util.Set<Integer> itemIds;
    
    @Size(max = 50)
    private String promoCode;

    public String getPromoCode()
    {
        return promoCode;
    }

    public java.util.Set<Integer> getItemIds()
    {
        return itemIds;
    }

}
