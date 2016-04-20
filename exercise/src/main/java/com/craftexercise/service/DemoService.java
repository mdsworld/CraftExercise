package com.craftexercise.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.craftexercise.entity.DemoRepository;
import com.craftexercise.entity.Item;
import com.craftexercise.entity.Order;
import com.craftexercise.entity.Promo;


@Service
public class DemoService
{
    private Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    public DemoRepository repository;
    
    @Value("${buffer.count}")
    private Integer bufferCount;
    
    //PROMO calls to DB
    public Promo getPromoByCode(String promoCode) throws Exception
    {
        logger.info("lookup call for promoCode- " + promoCode);
        Promo promo = repository.findPromoByCode(promoCode);
        if(promo==null)
            throw new Exception("Promo not found for promocode-" + promoCode);
        return promo;
    }
    
    public String addPromo(Promo promo)
    {
        logger.info("Adding new item to inventory- " + promo);
        int res = repository.addPromo(promo);
         if(res != 0)
             return "Added Successfully";
         else
             return "request failed";
    }

    // ORDER RELATED DB calls
    public Order placeOrder(Set<Integer> itemIds, String promoCode) throws Exception
    {
        Set<Item> items = new java.util.HashSet<Item>();
        BigDecimal totalAmt = new BigDecimal(0); String itemIdz="";
        for (Integer id : itemIds)
        {
            Item item = repository.findItemById(id);
            if(item == null || item.getQuantity() < 1)
                throw new Exception("Item not available - " + id);
            else{
                totalAmt = totalAmt.add(item.getPrice());
                itemIdz = item.getItemId() + "," + itemIdz;
                //decreasing count to be updated.
                item.setQuantity(item.getQuantity()-1);
                items.add(item);
            }
        }
        Integer promoId = null;
        if(promoCode != null){
            Promo promo = repository.findPromoByCode(promoCode);
            if(promo == null)
                throw new Exception("Promocode invalid - " + promoCode);
            BigDecimal promoAmt = promo.getPromoAmt();
            promoId = promo.getPromoId();
            totalAmt = totalAmt.subtract(promoAmt);
        }
        Random rand = new Random();
        Order order = new Order();
        order.setTotalPrice(totalAmt);
        order.setItemIds(itemIdz);
        order.setPromoId(promoId);
        order.setOrderNumber(rand.nextInt(10000)+"");        
        order = repository.addOrder(order);
        
        //persist updated Items. quantity decreased by 1.
        for (Item item : items)
        {
            repository.updateItem(item);
        }
        return order;
    }
    public BigDecimal calculateTotalAmt(Set<Integer> itemIds, String promoCode) throws Exception
    {
        BigDecimal totalAmt = new BigDecimal(0);
        for (Integer id : itemIds)
        {
            Item item = repository.findItemById(id);
            if(item == null )
                throw new Exception("Item not found for Id- " + id);
            else
                totalAmt = totalAmt.add(item.getPrice());
        }

        if(promoCode != null){
            Promo promo = repository.findPromoByCode(promoCode);
            if(promo == null)
                throw new Exception("Promocode invalid- " + promoCode);
            BigDecimal promoAmt = promo.getPromoAmt();
            totalAmt = totalAmt.subtract(promoAmt);
        }
        
        return totalAmt;
    }

    //Items
    public Item getItem(Integer itemId)
    {
        logger.info("lookup call for itemId- " + itemId);
        return repository.findItemById(itemId);
    }
    
    public java.util.List<Item> getAllInventoryItems()
    {
        logger.info("lookup for all inventory Items");
        return repository.getAllItems();
    }

    public java.util.List<Item> getCatalogItems() throws Exception
    {
        logger.info("lookup for all Items");
        java.util.List<Item> items = repository.getAllItems();
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();)
        {
            Item item = (Item) iterator.next();
            if(item.getQuantity() > bufferCount)
                item.setQuantity(item.getQuantity() - bufferCount);
            else{
                logger.info("Item " + item.getName()+"(" +item.getItemId()+")" +" sold out.");
                iterator.remove();
            }
                
        }
        return items;
    }
    
    public String addItem(Item item)
    {
        logger.info("Adding new item to inventory - " + item);
        if(repository.addItem(item) != 0)
            return "Added Successfully";
        else
            return "request failed";
    }

    public String updateItem(Item item)
    {
        logger.info("updated inventory for itemId- " + item.getItemId());
        if(repository.updateItem(item) != 0)
            return " updated Successfully";
        else
            return " request failed";
    }

}