package com.craftexercise.interfac;

import java.math.BigDecimal;


public class PlaceOrderResponse
{

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

}
