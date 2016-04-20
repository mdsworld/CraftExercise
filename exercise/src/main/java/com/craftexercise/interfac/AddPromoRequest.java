package com.craftexercise.interfac;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;


public class AddPromoRequest
{
    @NotNull(message = "'ispId' is required")
    private String promoCode;
    
    @NotNull(message = "'ispId' is required")
    private BigDecimal promoAmt;
    
    @NotNull(message = "'ispId' is required")
    private String description;
    
    public String getPromoCode()
    {
        return promoCode;
    }
    public void setPromoCode(String promoCode)
    {
        this.promoCode = promoCode;
    }
    public BigDecimal getPromoAmt()
    {
        return promoAmt;
    }
    public void setPromoAmt(BigDecimal promoAmt)
    {
        this.promoAmt = promoAmt;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    
}
