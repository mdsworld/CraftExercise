package com.craftexercise.interfac;

import com.craftexercise.interfac.model.BaseItem;

public class CatalogResponse
{

    java.util.List<BaseItem> items = new java.util.ArrayList<>();

    public java.util.List<BaseItem> getItems()
    {
        return items;
    }

    public void addAll(java.util.List<BaseItem> items)
    {
        this.items.addAll(items);
    }

    public void add(BaseItem item)
    {
        this.items.add(item);
    }
    
}
