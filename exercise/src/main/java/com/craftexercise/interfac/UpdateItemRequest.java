package com.craftexercise.interfac;

import javax.validation.constraints.NotNull;

public class UpdateItemRequest
{
    @NotNull(message = "'quantity' is required")
    private Long quantity;

    @NotNull(message = "'itemId' is required")
    private Integer itemId;

    public Long getQuantity()
    {
        return quantity;
    }
    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
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
