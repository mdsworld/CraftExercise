package com.craftexercise.interfac.model;

import java.math.BigDecimal;

public class Order {
    private BigDecimal totalPrice;
    private String orderNumber;

    public Order(String orderNumber, BigDecimal totalPrice)
    {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }


    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder
        .append(" orderNumber- ")
        .append(this.getOrderNumber())
        .append(" totalAmt- ")
        .append(this.getTotalPrice());
    
        return builder.toString();
      }

}