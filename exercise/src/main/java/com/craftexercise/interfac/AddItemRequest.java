package com.craftexercise.interfac;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddItemRequest
{
    @Size(max = 50)
    @NotNull(message = "'name' is required")
    private String name;
    
    @NotNull(message = "'price' is required")
    private BigDecimal price;
    
    @NotNull(message = "'quantity' is required")
    private Long quantity;

    @Size(max = 50)
    private String category;
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public BigDecimal getPrice()
    {
        return price;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    public Long getQuantity()
    {
        return quantity;
    }
    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
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
