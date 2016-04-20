package com.craftexercise.entity;

import java.math.BigDecimal;

public class Promo {
    private Integer promoId;
    private String promoCode;
    private BigDecimal promoAmt;
    private String description;

    public BigDecimal getPromoAmt()
    {
        return promoAmt;
    }

    public void setPromoAmt(BigDecimal promoAmt)
    {
        this.promoAmt = promoAmt;
    }

    public Integer getPromoId()
    {
        return promoId;
    }

    public void setPromoId(Integer promoId)
    {
        this.promoId = promoId;
    }

    public String getPromoCode()
    {
        return promoCode;
    }

    public void setPromoCode(String code)
    {
        this.promoCode = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder
        .append(", promoId- ")
        .append(this.getPromoId())
        .append(", code- ")
        .append(this.getPromoCode())
        .append(", descript- ")
        .append(this.getDescription())
        .append(", promoAmt- ")
        .append(this.getPromoAmt());
    
        return builder.toString();
      }

}