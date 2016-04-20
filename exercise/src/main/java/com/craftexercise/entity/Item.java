package com.craftexercise.entity;

import java.math.BigDecimal;

public class Item {
    private Integer itemId;
    private String name;
    private BigDecimal price;
    private Long quantity;
    private String category;

    public Integer getItemId()
    {
        return itemId;
    }

    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }

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


    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder
          .append(", itemId- ")
          .append(this.getItemId())
          .append(", name- ")
          .append(this.getName())
          .append(", quantity- ")
          .append(this.getQuantity())
          .append(", price- ")
          .append(this.getPrice())
          .append(", category- ")
          .append(this.getCategory());
    
        return builder.toString();
      }

}