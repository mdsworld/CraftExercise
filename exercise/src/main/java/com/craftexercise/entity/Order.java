package com.craftexercise.entity;

import java.math.BigDecimal;

public class Order {
    private Integer orderId;
    private String itemIds;
    private Integer promoId;
    private BigDecimal totalPrice;
    private String orderNumber;


    public Integer getPromoId()
    {
        return promoId;
    }


    public void setPromoId(Integer promoId)
    {
        this.promoId = promoId;
    }


    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }


    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }


    public Integer getOrderId()
    {
        return orderId;
    }


    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }


    public String getItemIds()
    {
        return itemIds;
    }


    public void setItemIds(String itemIds)
    {
        this.itemIds = itemIds;
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
        .append(" orderId- ")
        .append(this.getOrderId())
        .append(", orderNumber- ")
        .append(this.getOrderNumber())
        .append(", promoID- ")
        .append(this.getPromoId())
        .append(", itemIDs- ")
        .append(this.getItemIds());
    
        return builder.toString();
      }

}