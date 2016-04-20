package com.craftexercise.interfac;

import com.craftexercise.interfac.model.InventoryItem;

public class InventoryListResponse
{

    java.util.List<InventoryItem> items = new java.util.ArrayList<>();

    public java.util.List<InventoryItem> getItems()
    {
        return items;
    }

    public void add(InventoryItem item)
    {
        this.items.add(item);
    }
    
    public void addAll(java.util.List<InventoryItem> items)
    {
        this.items.addAll(items);
    }
}
