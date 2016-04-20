package com.craftexercise.interfac;

import com.craftexercise.interfac.model.Order;


public class ConfirmOrderResponse
{
    Order order;

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
    
}
