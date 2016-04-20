package com.craftexercise.interfac;

import java.util.List;
import com.craftexercise.entity.Item;
import com.craftexercise.entity.Promo;
import com.craftexercise.interfac.model.InventoryItem;
import com.craftexercise.interfac.model.Order;
import com.craftexercise.interfac.model.UserItem;

/**
 * A helper class to convert interfacing objects into internal entities and vice-versa. *
 */
public class DemoConverter
{

    public static CatalogResponse convertEntityToResponse(List<Item> allItems)
    {
        CatalogResponse resp = new CatalogResponse();
        
        if(org.springframework.util.CollectionUtils.isEmpty(allItems))
            return resp;
        
        for (Item item : allItems)
        {
            UserItem respItem = new UserItem(item.getItemId(), item.getName(), item.getPrice(),item.getCategory());
            resp.add(respItem);
        }
        return resp;
    }

    public static ConfirmOrderResponse convertEntityToResponse(com.craftexercise.entity.Order order)
    {
        if(order == null)
            return null;
        
        ConfirmOrderResponse resp = new ConfirmOrderResponse();
        Order ordResp = new Order(order.getOrderNumber(), order.getTotalPrice());
        resp.setOrder(ordResp);
        return resp;
    }


    public static InventoryListResponse conertEntityToResponse(com.craftexercise.entity.Item domain){
        InventoryListResponse respo = new InventoryListResponse();
        InventoryItem item = new InventoryItem(domain.getItemId(),domain.getName(), domain.getPrice(),domain.getCategory(),domain.getQuantity().intValue());
        respo.add(item);
        return respo;
    }

    public static InventoryListResponse conertEntityToResponse(List<Item> allItems)
    {
        InventoryListResponse respo = new InventoryListResponse();
        java.util.List<InventoryItem> items = new java.util.ArrayList<>();

        for (Item item : allItems)
        {
            InventoryItem invItem = new InventoryItem(item.getItemId(),item.getName(), item.getPrice(),item.getCategory(),item.getQuantity().intValue());
            items.add(invItem);
        }
        respo.addAll(items);
        return respo;
    }

    public static Item conertRequestToEntity(AddItemRequest request)
    {
        com.craftexercise.entity.Item item = new Item();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        item.setCategory(request.getCategory());
        return item;
    }

    public static Item convertRequestToEntity(UpdateItemRequest request)
    {
        com.craftexercise.entity.Item item = new Item();
        item.setItemId(request.getItemId());
        item.setQuantity(request.getQuantity());
        return item;
    }

    public static PromoResponse convertEntityToResponse(Promo promo)
    {
        if(promo== null)
            return null;
        PromoResponse resp = new PromoResponse();
        resp.setDescription(promo.getDescription());
        resp.setPromoAmt(promo.getPromoAmt());
        resp.setPromoCode(promo.getPromoCode());
        resp.setPromoId(promo.getPromoId());
        return resp;
    }

    public static Promo convertRequestToEntity(AddPromoRequest request)
    {
        Promo promo = new Promo();
        promo.setPromoCode(request.getPromoCode());
        promo.setPromoAmt(request.getPromoAmt());
        promo.setDescription(request.getDescription());
        return promo;
    }
}
